package com.taobao.tdhs.client.response;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

/**
 * @author <a href="mailto:wentong@taobao.com">文通</a>
 * @since 12-6-29 上午9:48
 */
public class TDHSBlob implements Blob {

    private final byte[] data;

    /**
     * Constructor TDHSBlob creates a new TDHSBlob instance.
     *
     * @param data of type byte[]
     */
    public TDHSBlob(byte[] data) {
        this.data = data;
    }

    /**
     * Method length ...
     *
     * @return long
     *
     * @throws SQLException when
     */
    public long length() throws SQLException {
        return data != null ? data.length : 0;
    }

    /**
     * Method getBytes ...
     *
     * @param pos    of type long
     * @param length of type int
     *
     * @return byte[]
     *
     * @throws SQLException when
     */
    public byte[] getBytes(long pos, int length) throws SQLException {
        if (data == null || data.length < pos + length) {
            throw new SQLException(
                    "getBytes out of range! " +
                            "the byte length[" + length() + "]," +
                            "request is pos [" + pos + "] length [" + length + "]");
        }
        byte[] r = new byte[length];
        System.arraycopy(data, (int) pos, r, 0, length);
        return r;
    }

    /**
     * Method getBinaryStream returns the binaryStream of this TDHSBlob object.
     *
     * @return the binaryStream (type InputStream) of this TDHSBlob object.
     *
     * @throws SQLException when
     */
    public InputStream getBinaryStream() throws SQLException {
        return data == null ? null : new ByteArrayInputStream(data);
    }

    /**
     * Method position ...
     *
     * @param pattern of type byte[]
     * @param start   of type long
     *
     * @return long
     *
     * @throws SQLException when
     */
    public long position(byte[] pattern, long start) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    /**
     * Method position ...
     *
     * @param pattern of type Blob
     * @param start   of type long
     *
     * @return long
     *
     * @throws SQLException when
     */
    public long position(Blob pattern, long start) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    /**
     * Method setBytes ...
     *
     * @param pos   of type long
     * @param bytes of type byte[]
     *
     * @return int
     *
     * @throws SQLException when
     */
    public int setBytes(long pos, byte[] bytes) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    /**
     * Method setBytes ...
     *
     * @param pos    of type long
     * @param bytes  of type byte[]
     * @param offset of type int
     * @param len    of type int
     *
     * @return int
     *
     * @throws SQLException when
     */
    public int setBytes(long pos, byte[] bytes, int offset, int len) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    /**
     * Method setBinaryStream ...
     *
     * @param pos of type long
     *
     * @return OutputStream
     *
     * @throws SQLException when
     */
    public OutputStream setBinaryStream(long pos) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    /**
     * Method truncate ...
     *
     * @param len of type long
     *
     * @throws SQLException when
     */
    public void truncate(long len) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    /**
     * Method free ...
     *
     * @throws SQLException when
     */
    public void free() throws SQLException {
    }

    /**
     * Method getBinaryStream ...
     *
     * @param pos    of type long
     * @param length of type long
     *
     * @return InputStream
     *
     * @throws SQLException when
     */
    public InputStream getBinaryStream(long pos, long length) throws SQLException {
        byte[] bytes = getBytes(pos, (int) length);
        return bytes == null ? null : new ByteArrayInputStream(bytes);
    }
}
