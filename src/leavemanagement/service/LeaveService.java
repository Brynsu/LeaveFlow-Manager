package leavemanagement.service;

import leavemanagement.request.LeaveRequest;

import java.util.ArrayList;

import java.util.List;

public class LeaveService {

    private List<LeaveRequest> requests;

    public LeaveService() {

        requests = new ArrayList<>();

    }

    public void addRequest(LeaveRequest request) {

        requests.add(request);

        System.out.println("Leave Request " + request.getRequestId() + " added to system.");

    }

    public LeaveRequest getRequestById(int requestId) {

        for (LeaveRequest request : requests) {

            if (request.getRequestId() == requestId) return request;

        }

        return null;

    }

    public List<LeaveRequest> getAllRequests() {

        return requests;

    }

}

