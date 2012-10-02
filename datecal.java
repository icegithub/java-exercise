//  java计算两个任意日期间的相差的天数(自然天)
// 分类： JAVA 2008-11-12 10:18 631人阅读 评论(0) 收藏 举报
public int computeCoolingOffPeriod(String sBeginDate,String sEndDate) 
{
    Calendar calendar1=Calendar.getInstance();
    Calendar calendar2=Calendar.getInstance();
    try
        {
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");//格式很重要：是20051031，还是2005-10-31格式呢？
            
            if (sBeginDate.equals("0")) 
                {
                    //System.out.println("sBirthDate.equals====0");   
                    sBeginDate = "19000101"; 
                }
            calendar1.setTime(formatter1.parse(sBeginDate));
            calendar2.setTime(formatter1.parse(sEndDate));
            
        }
    catch(Exception e)
        {
            e.printStackTrace();
        }
    return  (int)( (calendar2.getTimeInMillis()-calendar1.getTimeInMillis())/1000/60/60/24 );//获取天数的差值。
}
