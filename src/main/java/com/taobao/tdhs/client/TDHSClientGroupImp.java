package com.taobao.tdhs.client;

import java.util.concurrent.TimeUnit;

import com.taobao.tdhs.client.common.TDHSCommon.FindFlag;
import com.taobao.tdhs.client.easy.Query;
import com.taobao.tdhs.client.exception.TDHSException;
import com.taobao.tdhs.client.request.Filter;
import com.taobao.tdhs.client.request.Get;
import com.taobao.tdhs.client.request.Insert;
import com.taobao.tdhs.client.request.Update;
import com.taobao.tdhs.client.request.ValueEntry;
import com.taobao.tdhs.client.response.TDHSResponse;
import com.taobao.tdhs.client.statement.BatchStatement;
import com.taobao.tdhs.client.statement.Statement;
import com.taobao.tdhs.config.group.ConfigManager;
import com.taobao.tdhs.config.group.WeightSelector;

public class TDHSClientGroupImp implements TDHSClient {
	private static String VERSION = "2.4.1";
	private static String PREFIX = "com.taobao.tddl.jdbc.group_V" + VERSION + "_";
	private String dbGroupKey;
	private String fullDbGroupKey = null;
	private String appName;
	private ConfigManager configManager = new ConfigManager();
	public void init()
	{
		configManager.setAppName(appName);
		configManager.setFullGroupKey(getFullDbGroupKey());
		configManager.init();
		// db1:rwp0q0i1
	}
	public TDHSClient getClient(boolean isWrite)
	{
		int index = 0;
		if(isWrite)
		{
			WeightSelector ws = configManager.getWriteSelector();
			index = ws.getIndex();
			
		}
		else
		{
			WeightSelector ws = configManager.getReadSelector();
			index = ws.getIndex();
		}
		return configManager.getAtomTdhsClient().get(index);
	}
	
	public static String getFullDbGroupKey(String dbGroupKey) {
		return PREFIX + dbGroupKey;
	}
	
	public String getFullDbGroupKey() {
		if (fullDbGroupKey == null)
			fullDbGroupKey = PREFIX + getDbGroupKey();
		return fullDbGroupKey;
	}
	public String getDbGroupKey() {
		return dbGroupKey;
	}
	
	public TDHSResponse get(String db, String table, String index,
			String[] fields, String[][] keys) throws TDHSException {
		return getClient(false).get(db, table, index, fields, keys);
	}
	public String getCharsetName() {
		return getClient(true).getCharsetName();
	}
	public void setCharsetName(String charsetName) {
		throw new IllegalArgumentException("not supported yet");
	}
	public TDHSResponse get(String db, String table, String index,
			String[] fields, String[][] keys, FindFlag findFlag, int start,
			int limit, Filter[] filters) throws TDHSException {
		return getClient(false).get(db, table, index, fields, keys, findFlag, start,
				limit, filters);
	}
	public boolean isLowerCaseTableNames() {
		return getClient(true).isLowerCaseTableNames();
	}
	public void setLowerCaseTableNames(boolean lowerCaseTableNames) {
		throw new UnsupportedOperationException();
	}
	public boolean awaitForConnected(long timeout, TimeUnit unit) {
		return getClient(true).awaitForConnected(timeout, unit);
	}
	public Statement createStatement() {
		return getClient(true).createStatement();
	}
	public Statement createStatement(int hash) {
		return getClient(true).createStatement(hash);
	}
	public TDHSResponse get(Get get) throws TDHSException {
		return getClient(false).get(get);
	}
	public BatchStatement createBatchStatement() {
		return getClient(true).createBatchStatement();
	}
	public TDHSResponse count(String db, String table, String index,
			String[][] keys, FindFlag findFlag, int start, int limit,
			Filter[] filters) throws TDHSException {
		return getClient(false).count(db, table, index, keys, findFlag, start, limit,
				filters);
	}
	public void shutdown() {
		for(TDHSClient tdhsClient : configManager.getAtomTdhsClient())
		{
			tdhsClient.shutdown();
		}
	}
	public TDHSResponse count(Get get) throws TDHSException {
		return getClient(false).count(get);
	}
	public TDHSResponse delete(String db, String table, String index,
			String[][] keys, FindFlag findFlag, int start, int limit,
			Filter[] filters) throws TDHSException {
		return getClient(true).delete(db, table, index, keys, findFlag, start,
				limit, filters);
	}
	public TDHSResponse delete(Get get) throws TDHSException {
		return getClient(true).delete(get);
	}
	public TDHSResponse update(String db, String table, String index,
			String[] fields, ValueEntry[] valueEntry, String[][] keys,
			FindFlag findFlag, int start, int limit, Filter[] filters)
			throws TDHSException {
		return getClient(true).update(db, table, index, fields, valueEntry, keys,
				findFlag, start, limit, filters);
	}
	public TDHSResponse update(Update update) throws TDHSException {
		return getClient(true).update(update);
	}
	public TDHSResponse insert(String db, String table, String[] fields,
			String[] values) throws TDHSException {
		return getClient(true).insert(db, table, fields, values);
	}
	public TDHSResponse insert(Insert insert) throws TDHSException {
		return getClient(true).insert(insert);
	}
	public Query query() {
		return getClient(false).query();
	}
	public com.taobao.tdhs.client.easy.Insert insert() {
		return getClient(true).insert();
	}
	public void setDbGroupKey(String dbGroupKey) {
		this.dbGroupKey = dbGroupKey;
	}
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}
