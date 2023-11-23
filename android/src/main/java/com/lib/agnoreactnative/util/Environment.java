package com.lib.agnoreactnative.util;

public enum Environment {
    PRODUCTION(
            "https://player.agnoplay.com/static/api/v1/",
            "https://api.agnoplay.com/prod/"
    ),
    ACCEPTANCE(
            "https://player-acc.agnoplay.com/static/api/v1/",
            "https://api-acc.agnoplay.com/acc/"
    ),
    EGENIQ(
            "https://player-egeniq.agnoplay.com/static/api/v1/",
            "https://api-test.agnoplay.com/test/"
    );

    private final String baseUrl;
    private final String licenseBaseUrl;

    Environment(String baseUrl, String licenseBaseUrl) {
        this.baseUrl = baseUrl;
        this.licenseBaseUrl = licenseBaseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getLicenseBaseUrl() {
        return licenseBaseUrl;
    }
}

