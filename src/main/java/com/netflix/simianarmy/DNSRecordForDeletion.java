package com.netflix.simianarmy;

public class DNSRecordForDeletion {
    private final String dnsName;
    private final String dnsType;

    /**
     * @param dnsName
     *          the DNS record to delete
     * @param dnsType
 *          the DNS type (CNAME, A, or AAAA)
     */
    public DNSRecordForDeletion(String dnsName, String dnsType) {
        this.dnsName = dnsName;
        this.dnsType = dnsType;
    }

    public String getDnsName() {
        return dnsName;
    }

    public String getDnsType() {
        return dnsType;
    }
}
