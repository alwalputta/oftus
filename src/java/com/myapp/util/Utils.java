/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.util;

import java.nio.ByteBuffer;
import java.util.UUID;
import javax.persistence.Transient;

/**
 *
 * @author palwal
 */
public class Utils {

//    @Transient
//    private String uuid;
//    private byte[] id;
//
//    public String getId() {
//        if (this.id == null) {
//            UUID u = UUID.randomUUID();
//            ByteBuffer bb = ByteBuffer.allocate(16);
//
//            bb.putLong(u.getMostSignificantBits()).putLong(u.getLeastSignificantBits());
//            this.id = bb.array();
//            this.uuid = u.toString();
//        } else if (this.uuid == null) {
//            ByteBuffer bb = ByteBuffer.wrap(this.id);
//            UUID u = new UUID(bb.getLong(), bb.getLong());
//            this.uuid = u.toString();
//        }
//        return this.uuid;
//    }
    
    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    
}
