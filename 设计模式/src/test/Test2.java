package src.test;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Test2 {

    static class DwellTime {
        Long bugId;
        String empId;
        String empType;
        Long duration;
        String bugState;

        public Long getBugId() {
            return bugId;
        }

        public void setBugId(Long bugId) {
            this.bugId = bugId;
        }

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }

        public String getEmpType() {
            return empType;
        }

        public void setEmpType(String empType) {
            this.empType = empType;
        }

        public Long getDuration() {
            return duration;
        }

        public void setDuration(Long duration) {
            this.duration = duration;
        }

        public String getBugState() {
            return bugState;
        }

        public void setBugState(String bugState) {
            this.bugState = bugState;
        }
    }

    @Data
    static class ChangeLog {
        Long bugId;
        String verifierId;
        String assignedToId;
        Date dateIn;
        Date dateOut;
        Long durationTime;
        String bugStatus;

        public Long getBugId() {
            return bugId;
        }

        public void setBugId(Long bugId) {
            this.bugId = bugId;
        }

        public String getVerifierId() {
            return verifierId;
        }

        public void setVerifierId(String verifierId) {
            this.verifierId = verifierId;
        }

        public String getAssignedToId() {
            return assignedToId;
        }

        public void setAssignedToId(String assignedToId) {
            this.assignedToId = assignedToId;
        }

        public Date getDateIn() {
            return dateIn;
        }

        public void setDateIn(Date dateIn) {
            this.dateIn = dateIn;
        }

        public Date getDateOut() {
            return dateOut;
        }

        public void setDateOut(Date dateOut) {
            this.dateOut = dateOut;
        }

        public Long getDurationTime() {
            return durationTime;
        }

        public void setDurationTime(Long durationTime) {
            this.durationTime = durationTime;
        }

        public String getBugStatus() {
            return bugStatus;
        }

        public void setBugStatus(String bugStatus) {
            this.bugStatus = bugStatus;
        }
    }

    @Data
    static class KeludeChangeLog {
        Date createdAt;
        Long id;
        String newValue;
        String oldValue;
        String propertyKey;
        String propertyType;
        String targetId;
        String targetType;
        String userId;

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNewValue() {
            return newValue;
        }

        public void setNewValue(String newValue) {
            this.newValue = newValue;
        }

        public String getOldValue() {
            return oldValue;
        }

        public void setOldValue(String oldValue) {
            this.oldValue = oldValue;
        }

        public String getPropertyKey() {
            return propertyKey;
        }

        public void setPropertyKey(String propertyKey) {
            this.propertyKey = propertyKey;
        }

        public String getPropertyType() {
            return propertyType;
        }

        public void setPropertyType(String propertyType) {
            this.propertyType = propertyType;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getTargetType() {
            return targetType;
        }

        public void setTargetType(String targetType) {
            this.targetType = targetType;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    @Data
    static class DtAoneBugRecordDetailDTO {
        Long bugId;
        String verifierId;
        String assignedToId;

        public Long getBugId() {
            return bugId;
        }

        public void setBugId(Long bugId) {
            this.bugId = bugId;
        }

        public String getVerifierId() {
            return verifierId;
        }

        public void setVerifierId(String verifierId) {
            this.verifierId = verifierId;
        }

        public String getAssignedToId() {
            return assignedToId;
        }

        public void setAssignedToId(String assignedToId) {
            this.assignedToId = assignedToId;
        }
    }

    public static void main(String[] args) {
        DtAoneBugRecordDetailDTO dtAoneBugRecordDetailDTO = new DtAoneBugRecordDetailDTO();
        dtAoneBugRecordDetailDTO.setAssignedToId("907738");
        dtAoneBugRecordDetailDTO.setVerifierId("test");
        List<ChangeLog> changeLogList = Test2.findStayTimeWithEmpByBugId(dtAoneBugRecordDetailDTO);
        List<DwellTime> dwellTimeList = calculateTheDwellTime(changeLogList);
        List<DwellTime> list = new ArrayList<>(dwellTimeList.stream()
                .collect(Collectors.toMap(DwellTime::getEmpId, a -> a, (o1, o2) -> {
                    DwellTime dwellTime = new DwellTime();
                    dwellTime.setBugId(o1.getBugId());
                    dwellTime.setEmpId(o1.getEmpId());
                    dwellTime.setDuration(o1.getDuration() + o2.getDuration());
                    return dwellTime;
                })).values());
        System.out.println(list);
    }

    public static List<ChangeLog> findStayTimeWithEmpByBugId(DtAoneBugRecordDetailDTO bug) {
        Long bugId = bug.getBugId();

        String initVerifierId = null;
        String initAssignedToId = null;

        String json = "[\n" +
                "    {\n" +
                "        \"createdAt\": 1643099370000,\n" +
                "        \"id\": 62314233,\n" +
                "        \"newValue\": \"4\",\n" +
                "        \"oldValue\": \"3\",\n" +
                "        \"propertyKey\": \"status_stage\",\n" +
                "        \"propertyType\": \"attr\",\n" +
                "        \"targetId\": 39299354,\n" +
                "        \"targetType\": \"Issue\",\n" +
                "        \"userId\": 49988011\n" +
                "    },\n" +
                "    {\n" +
                "        \"createdAt\": 1643099370000,\n" +
                "        \"id\": 62314234,\n" +
                "        \"newValue\": \"33\",\n" +
                "        \"oldValue\": \"37\",\n" +
                "        \"propertyKey\": \"status_id\",\n" +
                "        \"propertyType\": \"attr\",\n" +
                "        \"targetId\": 39299354,\n" +
                "        \"targetType\": \"Issue\",\n" +
                "        \"userId\": 49988011\n" +
                "    },\n" +
                "    {\n" +
                "        \"createdAt\": 1642995152000,\n" +
                "        \"id\": 62284016,\n" +
                "        \"newValue\": \"37\",\n" +
                "        \"oldValue\": \"38\",\n" +
                "        \"propertyKey\": \"status_id\",\n" +
                "        \"propertyType\": \"attr\",\n" +
                "        \"targetId\": 39299354,\n" +
                "        \"targetType\": \"Issue\",\n" +
                "        \"userId\": 24397017\n" +
                "    },\n" +
                "    {\n" +
                "        \"createdAt\": 1642995146000,\n" +
                "        \"id\": 62284013,\n" +
                "        \"newValue\": \"3\",\n" +
                "        \"oldValue\": \"2\",\n" +
                "        \"propertyKey\": \"status_stage\",\n" +
                "        \"propertyType\": \"attr\",\n" +
                "        \"targetId\": 39299354,\n" +
                "        \"targetType\": \"Issue\",\n" +
                "        \"userId\": 24397017\n" +
                "    },\n" +
                "    {\n" +
                "        \"createdAt\": 1642995146000,\n" +
                "        \"id\": 62284014,\n" +
                "        \"newValue\": \"38\",\n" +
                "        \"oldValue\": \"30\",\n" +
                "        \"propertyKey\": \"status_id\",\n" +
                "        \"propertyType\": \"attr\",\n" +
                "        \"targetId\": 39299354,\n" +
                "        \"targetType\": \"Issue\",\n" +
                "        \"userId\": 24397017\n" +
                "    },\n" +
                "    {\n" +
                "        \"createdAt\": 1642754643000,\n" +
                "        \"id\": 62252789,\n" +
                "        \"newValue\": \"2\",\n" +
                "        \"oldValue\": \"3\",\n" +
                "        \"propertyKey\": \"status_stage\",\n" +
                "        \"propertyType\": \"attr\",\n" +
                "        \"targetId\": 39299354,\n" +
                "        \"targetType\": \"Issue\",\n" +
                "        \"userId\": 32230328\n" +
                "    },\n" +
                "    {\n" +
                "        \"createdAt\": 1642754643000,\n" +
                "        \"id\": 62252790,\n" +
                "        \"newValue\": \"30\",\n" +
                "        \"oldValue\": \"37\",\n" +
                "        \"propertyKey\": \"status_id\",\n" +
                "        \"propertyType\": \"attr\",\n" +
                "        \"targetId\": 39299354,\n" +
                "        \"targetType\": \"Issue\",\n" +
                "        \"userId\": 32230328\n" +
                "    },\n" +
                "    {\n" +
                "        \"createdAt\": 1642749062000,\n" +
                "        \"id\": 62249415,\n" +
                "        \"newValue\": \"3\",\n" +
                "        \"oldValue\": \"1\",\n" +
                "        \"propertyKey\": \"status_stage\",\n" +
                "        \"propertyType\": \"attr\",\n" +
                "        \"targetId\": 39299354,\n" +
                "        \"targetType\": \"Issue\",\n" +
                "        \"userId\": 24397017\n" +
                "    },\n" +
                "    {\n" +
                "        \"createdAt\": 1642749062000,\n" +
                "        \"id\": 62249416,\n" +
                "        \"newValue\": \"37\",\n" +
                "        \"oldValue\": \"28\",\n" +
                "        \"propertyKey\": \"status_id\",\n" +
                "        \"propertyType\": \"attr\",\n" +
                "        \"targetId\": 39299354,\n" +
                "        \"targetType\": \"Issue\",\n" +
                "        \"userId\": 24397017\n" +
                "    },\n" +
                "    {\n" +
                "        \"createdAt\": 1642748925000,\n" +
                "        \"id\": 62249341,\n" +
                "        \"newValue\": \"24397017\",\n" +
                "        \"oldValue\": \"20992524\",\n" +
                "        \"propertyKey\": \"assigned_to_id\",\n" +
                "        \"propertyType\": \"attr\",\n" +
                "        \"targetId\": 39299354,\n" +
                "        \"targetType\": \"Issue\",\n" +
                "        \"userId\": 20992524\n" +
                "    },\n" +
                "    {\n" +
                "        \"createdAt\": 1642748639000,\n" +
                "        \"id\": 62249119,\n" +
                "        \"newValue\": \"20992524\",\n" +
                "        \"oldValue\": \"907738\",\n" +
                "        \"propertyKey\": \"assigned_to_id\",\n" +
                "        \"propertyType\": \"attr\",\n" +
                "        \"targetId\": 39299354,\n" +
                "        \"targetType\": \"Issue\",\n" +
                "        \"userId\": 907738\n" +
                "    },\n" +
                "    {\n" +
                "        \"createdAt\": 1642747446000,\n" +
                "        \"id\": 62248401,\n" +
                "        \"newValue\": \"28\",\n" +
                "        \"propertyKey\": \"status_id\",\n" +
                "        \"propertyType\": \"attr\",\n" +
                "        \"targetId\": 39299354,\n" +
                "        \"targetType\": \"Issue\",\n" +
                "        \"userId\": 49988011\n" +
                "    }\n" +
                "]";
        List<KeludeChangeLog> issueChangeLog = JSONArray.parseArray(json, KeludeChangeLog.class);
        Collections.reverse(issueChangeLog);

        List<KeludeChangeLog> filterLog = new ArrayList<>();
        for (KeludeChangeLog changeLog : issueChangeLog) {
            if ("attr".equals(changeLog.getPropertyType()) && "status_id".equals(changeLog.getPropertyKey())) {
                filterLog.add(changeLog);
            } else if ("attr".equals(changeLog.getPropertyType()) && "assigned_to_id".equals(changeLog.getPropertyKey())) {
                // 获取第一个指派人
                if (initAssignedToId == null) {
                    initAssignedToId = changeLog.getOldValue();
                }
                filterLog.add(changeLog);
            } else if ("attr".equals(changeLog.getPropertyType()) && "verifier_id".equals(changeLog.getPropertyKey())) {
                // 获取第一个验证人
                if (initVerifierId == null) {
                    initVerifierId = changeLog.getOldValue();
                }
                filterLog.add(changeLog);
            }
        }

        // 如果指派人没有changeLog，取现有的指派人
        if (initAssignedToId == null) {
            initAssignedToId = bug.getAssignedToId();
        }
        // 如果验证人没有changeLog, 取现有的验证人
        if (initVerifierId == null) {
            initVerifierId = bug.getVerifierId();
        }

        List<ChangeLog> resultList = new ArrayList<>();
        String bugStatus = null;
        for (int i = 0; i< filterLog.size(); i++) {
            KeludeChangeLog current = filterLog.get(i);
            KeludeChangeLog next = null;
            if (i < filterLog.size() -1) {
                next = filterLog.get(i + 1);
            }

            if ("attr".equals(current.getPropertyType()) && "assigned_to_id".equals(current.getPropertyKey())) {
                initAssignedToId = current.getNewValue();
            } else if ("attr".equals(current.getPropertyType()) && "verifier_id".equals(current.getPropertyKey())) {
                initVerifierId = current.getNewValue();
            } else if ("attr".equals(current.getPropertyType()) && "status_id".equals(current.getPropertyKey())) {
                bugStatus = current.getNewValue();
            }

            if (bugStatus == null) {
                throw new RuntimeException("获取bug状态失败");
            }
            Date dateIn = current.getCreatedAt();
            Date dateOut = null;
            if (next != null) {
                dateOut = next.getCreatedAt();
            }
            ChangeLog changeLog = new ChangeLog();
            changeLog.setBugId(bugId);
            changeLog.setBugStatus(bugStatus);
            changeLog.setDateIn(dateIn);
            changeLog.setDateOut(dateOut);
            changeLog.setAssignedToId(initAssignedToId);
            changeLog.setVerifierId(initVerifierId);
            resultList.add(changeLog);
        }
        return resultList;
    }

    public static List<DwellTime> calculateTheDwellTime (List<ChangeLog> changeLogList) {
        List<DwellTime> dwellTimeList = new ArrayList<>();
        for (ChangeLog changeLog : changeLogList) {
            DwellTime dwellTime = doCalculateTheDwellTime(changeLog);
            if (dwellTime != null) {
                dwellTimeList.add(dwellTime);
            }
        }
        return dwellTimeList;
    }

    private static DwellTime doCalculateTheDwellTime(ChangeLog changeLog) {
        //28:new,30:reopen30,open:???,external:38,later???
        DwellTime dwellTime = new DwellTime();
        dwellTime.setBugId(changeLog.getBugId());
        dwellTime.setBugState(changeLog.getBugStatus());
        Date dateIn = changeLog.getDateIn();
        Date dateOut = changeLog.getDateOut();
        if (dateOut == null) {
            if ("33".equals(changeLog.getBugStatus())) {
                // bug已关闭
                return null;
            }
            // bug未关闭
            dateOut = new Date();
        }
        long duration = dateOut.getTime() - dateIn.getTime();
        dwellTime.setDuration(duration);
        String bugStatus = changeLog.getBugStatus();
        if ("28".equals(bugStatus) || "30".equals(bugStatus) || "38".equals(bugStatus)) {
            // bug停留在指派人
            dwellTime.setEmpType("assignedTo");
            dwellTime.setEmpId(changeLog.getAssignedToId());

        } else {
            dwellTime.setEmpType("verifier");
            dwellTime.setEmpId(changeLog.getVerifierId());
        }
        return dwellTime;
    }
}
