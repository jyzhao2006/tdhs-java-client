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
import com.taobao.tdhs.config.AtomAlreadyInitException;
import com.taobao.tdhs.config.TAtomDsConfHandle;
public class TDHSClientAtomImp implements TDHSClient {

	TAtomDsConfHandle atomTdhs = new TAtomDsConfHandle();
	
	public void setAppName(String appName) throws AtomAlreadyInitException {
		atomTdhs.setAppName(appName);
	}

	public void setDbKey(String dbKey) throws AtomAlreadyInitException {
		atomTdhs.setDbKey(dbKey);
	}

	public String getAppName() {
		return atomTdhs.getAppName();
	}

	public String getDbKey() {
		return atomTdhs.getDbKey();
	}

	public void init() throws Exception
	{
		atomTdhs.init();
	}
	public TDHSResponse get(String db, String table, String index,
			String[] fields, String[][] keys) throws TDHSException {
		return atomTdhs.getTdhsClient().get(db, table, index, fields, keys);
	}
	public String getCharsetName() {
		return atomTdhs.getTdhsClient().getCharsetName();
	}
	public void setCharsetName(String charsetName) {
		atomTdhs.getTdhsClient().setCharsetName(charsetName);
	}
	public TDHSResponse get(String db, String table, String index,
			String[] fields, String[][] keys, FindFlag findFlag, int start,
			int limit, Filter[] filters) throws TDHSException {
		return atomTdhs.getTdhsClient().get(db, table, index, fields, keys, findFlag, start,
				limit, filters);
	}
	public boolean isLowerCaseTableNames() {
		return atomTdhs.getTdhsClient().isLowerCaseTableNames();
	}
	public void setLowerCaseTableNames(boolean lowerCaseTableNames) {
		atomTdhs.getTdhsClient().setLowerCaseTableNames(lowerCaseTableNames);
	}
	public boolean awaitForConnected(long timeout, TimeUnit unit) {
		return atomTdhs.getTdhsClient().awaitForConnected(timeout, unit);
	}
	public Statement createStatement() {
		return atomTdhs.getTdhsClient().createStatement();
	}
	public Statement createStatement(int hash) {
		return atomTdhs.getTdhsClient().createStatement(hash);
	}
	public TDHSResponse get(Get get) throws TDHSException {
		return atomTdhs.getTdhsClient().get(get);
	}
	public BatchStatement createBatchStatement() {
		return atomTdhs.getTdhsClient().createBatchStatement();
	}
	public TDHSResponse count(String db, String table, String index,
			String[][] keys, FindFlag findFlag, int start, int limit,
			Filter[] filters) throws TDHSException {
		return atomTdhs.getTdhsClient().count(db, table, index, keys, findFlag, start, limit,
				filters);
	}
	public void shutdown() {
		atomTdhs.getTdhsClient().shutdown();
	}
	public TDHSResponse count(Get get) throws TDHSException {
		return atomTdhs.getTdhsClient().count(get);
	}
	public TDHSResponse delete(String db, String table, String index,
			String[][] keys, FindFlag findFlag, int start, int limit,
			Filter[] filters) throws TDHSException {
		return atomTdhs.getTdhsClient().delete(db, table, index, keys, findFlag, start,
				limit, filters);
	}
	public TDHSResponse delete(Get get) throws TDHSException {
		return atomTdhs.getTdhsClient().delete(get);
	}
	public TDHSResponse update(String db, String table, String index,
			String[] fields, ValueEntry[] valueEntry, String[][] keys,
			FindFlag findFlag, int start, int limit, Filter[] filters)
			throws TDHSException {
		return atomTdhs.getTdhsClient().update(db, table, index, fields, valueEntry, keys,
				findFlag, start, limit, filters);
	}
	public TDHSResponse update(Update update) throws TDHSException {
		return atomTdhs.getTdhsClient().update(update);
	}
	public TDHSResponse insert(String db, String table, String[] fields,
			String[] values) throws TDHSException {
		return atomTdhs.getTdhsClient().insert(db, table, fields, values);
	}
	public TDHSResponse insert(Insert insert) throws TDHSException {
		return atomTdhs.getTdhsClient().insert(insert);
	}
	public Query query() {
		return atomTdhs.getTdhsClient().query();
	}
	public com.taobao.tdhs.client.easy.Insert insert() {
		return atomTdhs.getTdhsClient().insert();
	}
	
}
