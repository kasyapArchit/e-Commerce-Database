package dao;

import beans.Complaint;

public interface ComplaintDAO {
    public void AddComplaint (Complaint complaint);
    public Complaint GetComplaintByID (Long complaint_id);
    public void Resolve (Long complaint_id);
}