package coding;


import java.text.SimpleDateFormat;
import java.util.*;

class DateMerger{
    public static List<DateRange> dlist = new ArrayList<DateRange>();
    public static List<DateRange> result = new ArrayList<DateRange>();
    public static  List<DateRange> local = new ArrayList<>();

    public static Date parseDate(String date){
        Date result=new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try{
            result=format.parse(date);
        }catch(Exception e){
        }
        return result;
    }

    public static void addDates(String start, String end)throws Exception{
        DateRange d= new DateRange();
        d.setValues(parseDate(start),parseDate(end));
        dlist.add(d);
    }

    public static List<DateRange> mergeDates(List<DateRange> dateRanges){
       local.add(dateRanges.get(0));
        for(int i=1;i<dateRanges.size();i++){
                if((dateRanges.get(i).startDate).compareTo(dateRanges.get(i-1).endDate)<0){
                   local.add(dateRanges.get(i));
                }
                else{ 
                   fillCache();
                    local.add(dateRanges.get(i));                
                }
        }
        fillCache();
        return result;
    }
    public static void fillCache(){
        if(local.size()>0){
            DateRange o = new DateRange();
            o.startDate=local.get(0).startDate;
            o.endDate=local.get((local.size())-1).endDate;
            result.add(o);
            local.clear();
        }
    }
    public static void main(String[] args)throws Exception {
        addDates("01/01/2014", "30/01/2014");
        addDates("15/01/2014", "15/02/2014");
        addDates("10/03/2014", "15/04/2014");
        addDates("10/04/2014", "15/05/2014");
        List<DateRange> res=mergeDates(dlist);
        res.forEach(value->System.out.println(value.startDate+" "+"--"+" "+value.endDate+"\n"));
    }

}

class DateRange{
    public Date startDate;
    public Date endDate;
    public DateRange(){}
    public void setValues(Date start,Date end){
        startDate=start;
        endDate=end;
    }
}
