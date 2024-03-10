package org.example;

public enum ProviderEnum {
    EVN_HCMC("EVN HCMC"),
    SAVACO_HCMC("SAVACO HCMC"),
    VNPT("VNPT");

    ProviderEnum(String label) {
        this.label = label;
    }

    private final String label;

    public String getLabel() {
        return label;
    }
}
