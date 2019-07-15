package com.tje.webapp.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

public class BroadCastController extends TextWebSocketHandler {
	class WebSocketClientInfo {
		private WebSocketSession session;
		private String alias;
		
		public WebSocketClientInfo(WebSocketSession session) {
			this.session = session;
		}

		public WebSocketSession getSession() {
			return session;
		}

		public void setSession(WebSocketSession session) {
			this.session = session;
		}

		public String getAlias() {
			return alias;
		}

		public void setAlias(String alias) {
			this.alias = alias;
		}
		
	}
	
	// 현재 웹 서버에 접속중인 웹소켓 클라이언들을 저장하는 MAP 객체
	private Hashtable<String, WebSocketClientInfo> sessionMap = new Hashtable<String, WebSocketClientInfo>();
	
	@Override
	public void afterConnectionEstablished(
			WebSocketSession session) throws Exception {
		// 클라이언트가 연결되면 세션 아이디의 값을 출력
		System.out.printf("%s가 연결됨\n", session.getId());
		
		// 접속된 클라이언트의 세션 객체를 저장
		sessionMap.put(session.getId(), 
				new WebSocketClientInfo(session));
	}
	
	private void sendClientAliases(String id) throws Exception {
		StringBuffer idAndAliases = new StringBuffer("newClient:"); 
		idAndAliases.append(String.format("%s (%s),", 
				sessionMap.get(id).getAlias(), id));
		TextMessage message = new TextMessage(
				idAndAliases.toString());
		for( String key : sessionMap.keySet() ) {
			if( !key.equals(id))
				sessionMap.get(key).getSession().sendMessage(message);
		}
				
	}
	
	// 웹 소켓 클라이언트가 서버측으로 데이터를 전송할 때
	// 실행되는 메소드
	@Override
	protected void handleTextMessage(
			WebSocketSession session, TextMessage message) throws Exception {
		// 각 세션의 첫번째 메세지 전달 코드는 별칭 전달
		String id = session.getId();
		if( sessionMap.get(id).getAlias() == null ) {
			StringBuffer idAndAliases = new StringBuffer("newClient:"); 
			for( String key : sessionMap.keySet() ) {
				System.out.printf("%s, %s\n", id, key);
				if( key.equals(id) ) {
					System.out.printf("%s, %s - continue\n", id, key);
					continue;
				}
				
				idAndAliases.append(String.format("%s (%s),", 
						sessionMap.get(key).getAlias(), key));	
			}		
			
			session.sendMessage(
					new TextMessage(
							idAndAliases.toString()));
			
			sessionMap.get(id).setAlias(message.getPayload());
			
			sendClientAliases(id);
			return;
		}
		
		// 클라이언트가 전송한 데이터를 출력
		System.out.printf("%s로부터 [%s]를 받음\n", 
				sessionMap.get(id).getAlias(), 
				message.getPayload());
		
		StringTokenizer st = new StringTokenizer(message.getPayload(), ":@");
		st.nextToken();	// to
		String target = st.nextToken();	// target ID
		String msg = st.nextToken();	// message
		
		if( !target.equals("all") ) {
			sessionMap.get(target).getSession().sendMessage(
				new TextMessage(
					"(" + sessionMap.get(id).getAlias() + " 님으로부터 쪽지) : " + msg));
		} else {		
			// 메세지를 보낸 클라이언트를 제외한 모든 클라이언트들에게 메세지 전송		
			for( String key : sessionMap.keySet() ) {
				if( key.equals(id) )
					continue;
				
				sessionMap.get(key).getSession().sendMessage(
						new TextMessage(sessionMap.get(id).getAlias() + " : " + message.getPayload()));
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(
			WebSocketSession session, CloseStatus status) throws Exception {
		System.out.printf("%s가 연결 종료됨\n", session.getId());
		
		// 연결이 종료된 클라이언트를 세션 목록에서 제거
		WebSocketClientInfo closedClient = 
				sessionMap.remove(session.getId());
		
		String strClosedClient = 
				String.format("closed:%s (%s)", 
						closedClient.getAlias(), session.getId());
		
		for( String key : sessionMap.keySet() ) {
			sessionMap.get(key).getSession().sendMessage(
					new TextMessage(strClosedClient));
		}
	}
}












