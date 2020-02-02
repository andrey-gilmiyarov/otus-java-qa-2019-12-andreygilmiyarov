public enum BrandForComparison {

    XIAOMI(false), HUAWEI(false);

    private boolean compareToThisBrand;

    BrandForComparison(boolean compareToThisBrand) {
        this.compareToThisBrand = compareToThisBrand;
    }

    public boolean isCompareToThisBrand() {
        return compareToThisBrand;
    }

    public void setCompareToThisBrand(boolean compareToThisBrand) {
        this.compareToThisBrand = compareToThisBrand;
    }

}
