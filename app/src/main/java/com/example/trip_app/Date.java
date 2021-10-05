package com.example.trip_app;

import android.widget.EditText;

public class Date {
    private long ID;
    private String TRIPNAME;
    private  String STARTPOINT;
    private  String ENDPOINT;
    private  String DATE;
    private  String TIME;
   // private  String TRIPREMENDIER;
  //  private  String TRIPTYPE;


    public Date(long ID,String tripName, String startpoint, String endpoint, String DATE, String TIME )
    {
        this.ID=ID;
        this.TRIPNAME=tripName;
        this.STARTPOINT=startpoint;
        this.ENDPOINT=endpoint;
        this.DATE=DATE;
        this.TIME=TIME;
       // this.TRIPREMENDIER=tRIPREMENDIER;
       // this.TRIPTYPE=tRIPTYPE;

    }

    public Date(String TRIPNAME,String STARTPOINT, String ENDPOINT,String DATE,String TIME)
    {
        this.TRIPNAME=TRIPNAME;
        this.STARTPOINT=STARTPOINT;
        this.ENDPOINT=ENDPOINT;
        this.DATE=DATE;
        this.TIME=TIME;
       // this.TRIPREMENDIER=TRIPREMENDIER;
      //  this.TRIPTYPE=TRIPTYPE;
    }

    public Date() {

    }


    public long getID()
    {
        return ID;
    }

    public String getTRIPNAME()
    {
        return TRIPNAME;
    }
    public String getSTARTPOINT()
    {
        return STARTPOINT;
    }
    public String getENDPOINT()
    {
        return ENDPOINT;
    }
    public String getTIME()
    {
        return TIME;
    }
    public String getDATE()
    {
        return DATE;
    }
 //   public String getTRIPREMENDIER(){return TRIPREMENDIER;}
//public  String getTRIPTYPE(){return TRIPTYPE;}
    public void setID(long ID)
    {
        this.ID = ID;
    }

    public  void  setTRIPNAME(String TRIPNAME)
    {
        this.TRIPNAME= TRIPNAME;
    }
    public  void  setSTARTPOINT(String STARTPOINT)
    {
        this.STARTPOINT= STARTPOINT;
    }
    public  void  setENDPOINT(String ENDPOINT)
    {
        this.ENDPOINT= ENDPOINT;
    }
    public  void  setDATE(String DATE)
    {
        this.DATE= DATE;
    }
    public  void  setTIME(String TIME)
    {
        this.TIME= TIME;
    }
    //public void  setTRIPREMENDIER(String TRIPREMENDIER){this.TRIPREMENDIER=TRIPREMENDIER;}
   /* public void setTRIPTYPE(String TRIPTYPE){this.TRIPTYPE=TRIPTYPE;}*/
}
