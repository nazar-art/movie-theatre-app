package net.lelyak.edu.entity;

public class AspectModel extends BaseEntity {

    private Long aspectCount;
    private String target;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Long getAspectCount() {
        return aspectCount;
    }

    public void setAspectCount(Long aspectCount) {
        this.aspectCount = aspectCount;
    }
}
