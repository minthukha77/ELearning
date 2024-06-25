package com.blissstock.mappingSite.model;

public class BreadcrumbLists {
    String breadcrumbName;
    String breadcrumbURL;
    String breadcrumbStatus;


    public BreadcrumbLists() {
    }

    public BreadcrumbLists(String breadcrumbName, String breadcrumbURL, String breadcrumbStatus) {
        this.breadcrumbName = breadcrumbName;
        this.breadcrumbURL = breadcrumbURL;
        this.breadcrumbStatus = breadcrumbStatus;
    }

    public String getBreadcrumbName() {
        return this.breadcrumbName;
    }

    public void setBreadcrumbName(String breadcrumbName) {
        this.breadcrumbName = breadcrumbName;
    }

    public String getBreadcrumbURL() {
        return this.breadcrumbURL;
    }

    public void setBreadcrumbURL(String breadcrumbURL) {
        this.breadcrumbURL = breadcrumbURL;
    }

    public String getBreadcrumbStatus() {
        return this.breadcrumbStatus;
    }

    public void setBreadcrumbStatus(String breadcrumbStatus) {
        this.breadcrumbStatus = breadcrumbStatus;
    }

}
