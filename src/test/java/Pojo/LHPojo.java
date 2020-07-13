package Pojo;

import java.util.List;

public class LHPojo {

    private String expand;
    private String startAt;
    private String maxResults;
    private String total;
    private List<LHIssuesPojo> issues;

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(String maxResults) {
        this.maxResults = maxResults;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<LHIssuesPojo> getIssues() {
        return issues;
    }

    public void setIssues(List<LHIssuesPojo> issues) {
        this.issues = issues;
    }
}
