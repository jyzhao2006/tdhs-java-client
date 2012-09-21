/*
 * Copyright(C) 2011-2012 Alibaba Group Holding Limited
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License version 2 as
 *  published by the Free Software Foundation.
 *
 *  Authors:
 *    wentong <wentong@taobao.com>
 */

package com.taobao.tdhs.client.common;

import com.taobao.tdhs.client.protocol.TDHSProtocol;
import com.taobao.tdhs.client.protocol.TDHSProtocolBinary;

/**
 * @author <a href="mailto:wentong@taobao.com">文通</a>
 * @since 11-11-1 上午10:51
 */
public final class TDHSCommon {

    public static final int REQUEST_MAX_FIELD_NUM = 256;

    public static final int REQUEST_MAX_KEY_NUM = 100;

    public static final TDHSProtocol PROTOCOL_FOR_BINARY = new TDHSProtocolBinary();

    public enum RequestType {
        GET(0), COUNT(1), UPDATE(10), DELETE(11), INSERT(12), BATCH(20), SHAKE_HAND(0xFFFF);

        private int value;

        RequestType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    public enum FindFlag {
        TDHS_EQ(0), TDHS_GE(1), TDHS_LE(2), TDHS_GT(3), TDHS_LT(4), TDHS_IN(5), TDHS_DEQ(6);

        private int value;

        FindFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    public enum FilterFlag {
        TDHS_EQ(0), TDHS_GE(1), TDHS_LE(2), TDHS_GT(3), TDHS_LT(4), TDHS_NOT(5);

        private int value;

        FilterFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    public enum UpdateFlag {
        TDHS_UPDATE_SET(0), TDHS_UPDATE_ADD(1), TDHS_UPDATE_SUB(2), TDHS_UPDATE_NOW(3);

        private int value;

        UpdateFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }


}
