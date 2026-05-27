package com.example.demo.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class SwapRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String leftCcn;
    private String rightCcn;
    private String remark;
    private LocalDateTime timestamp;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLeftCcn() { return leftCcn; }
    public void setLeftCcn(String leftCcn) { this.leftCcn = leftCcn; }

    public String getRightCcn() { return rightCcn; }
    public void setRightCcn(String rightCcn) { this.rightCcn = rightCcn; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
