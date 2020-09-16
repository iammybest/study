package com.iammybest.hbase.springboot.hbase.domain;

import com.iammybest.hbase.springboot.hbase.protobuf.VehicleStatusItems;
import com.timanetworks.tpc.dao.base.annotation.*;
import com.timanetworks.tpc.dao.base.environment.RowKeyPartNameFrom;
import com.timanetworks.tpc.dao.base.rowkey.LongRowKey;
import com.timanetworks.tpc.dao.base.rowkey.RowKey;
import com.timanetworks.tpc.dao.base.rowkey.StringRowKey;
import com.timanetworks.tpc.dao.base.rowkey.StructRowKey;
import com.timanetworks.tpc.dao.base.util.EntityUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ming on 6/24/15.
 */

@Entity
@Table(name = "AudiVehicleStatusDataNational")
@TableColumnFamily(name = VehicleStatusInfoTable.ColumnFamily.DATA)
@RowKeyTemplate(sequenceEnabled = true, value = {
        @RowKeyPart(name = "data.tenantId", type = String.class, from = RowKeyPartNameFrom.COLUMN),
        @RowKeyPart(name = "data.vin",type = String.class, from = RowKeyPartNameFrom.COLUMN),
        @RowKeyPart(name = "data.eventId",type = String.class, from = RowKeyPartNameFrom.COLUMN),
        @RowKeyPart(name = "data.reportTime",type = long.class, from = RowKeyPartNameFrom.COLUMN)
})
public class VehicleStatusInfoTable implements Serializable {

    public static class ColumnFamily {
        public static final String DATA = EntityUtils.DEFAULT_COLUMN_FAMILY_NAME;
        public static final String STATUS_ITEM = "StatusItem";
    }


    @Id
    @Column(length = 255, unique = true, nullable = false)
    private String id;

    @Column(name="vin", nullable = false)
    private String vin;

    @Column(name = "reportTime")
    @Temporal(value = TemporalType.TIMESTAMP)
    private long reportTime;

    @Column(name = "eventId", nullable = false)
    private String eventId;

    @Column(name = "tenantId", nullable = false)
    private String tenantId;

    @Column(name = "deviceId", nullable = false)
    private String deviceId;

    @FieldColumnFamily(name = ColumnFamily.STATUS_ITEM, type = FieldColumnFamily.Type.PROTOBUF)
    @EmbeddedProtoBuf(clazz = VehicleStatusItems.class)
    @OneToMany(mappedBy = "items", fetch = FetchType.EAGER)
    private List<VehicleStatusValue> items;


    public static byte[] partialKey(String vin, String tenantId, String eventId, long reportTime) {
        if (vin == null)
            throw new NullPointerException("vin is null for partial key");
        StructRowKey structRowKey = new StructRowKey(new RowKey[]{new StringRowKey(), new StringRowKey(), new StringRowKey(), new LongRowKey()});
        try {
            return structRowKey.serialize(new Object[]{tenantId, vin, eventId,reportTime});
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static byte[] partialKey(String vin, String tenantId, String eventId) {
        if (vin == null)
            throw new NullPointerException("vin is null for partial key");
        StructRowKey structRowKey = new StructRowKey(new RowKey[]{new StringRowKey(), new StringRowKey(), new StringRowKey()});
        try {
            return structRowKey.serialize(new Object[]{tenantId, vin, eventId});
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static byte[] partialKey(String vin, String tenantId) {
        if (vin == null)
            throw new NullPointerException("vin is null for partial key");
        StructRowKey structRowKey = new StructRowKey(new RowKey[]{new StringRowKey(), new StringRowKey()});
        try {
            return structRowKey.serialize(new Object[]{tenantId, vin});
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).
                append("vin", vin).
                append("reportTime", reportTime).
                append("eventId", eventId).
                append("tenantId", tenantId).
                toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public long getReportTime() {
        return reportTime;
    }

    public void setReportTime(long reportTime) {
        this.reportTime = reportTime;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<VehicleStatusValue> getItems() {
        return items;
    }

    public void setItems(List<VehicleStatusValue> items) {
        this.items = items;
    }
}
