package com.tje.services;

import java.sql.*;
import java.util.*;

public interface DAO {
	public abstract boolean select(Connection conn, Member ojb);
	public abstract boolean insert(Connection conn, Member ojb);
}
