package com.zrp.apartday;
/**
 * 这个类主要的功能是计算任意两个日期的相隔天数，主要算法思想是
 * 1.计算开始日期到该年结束日期的相隔天数 如：开始日期为：2009-07-12 则求出到该日期到 2009-12-31日的相隔天数
 * 2.计算结束日期到改年开始日期的相隔天数 如：结束日期为: 2025-08-05 则求出该日期到 2025-01-01
 * 3.计算出2010年到2024年的所有的天数
 * 4.把以上所有的天数相加
 * @author Administrator
 *
 */
public class DateApartDays {
 /**
  * This is constructed function
  */
    public DateApartDays(){};
  
  /**
   * 比较两个日期大小,写成静态方法
   * @param startDate,endDate
   * @return boolean
   */
    public boolean DateCompare(String startDate,String endDate){
  boolean begBinggerEnd = Integer.valueOf(startDate)<=Integer.valueOf(endDate);
  try
  {
     assert begBinggerEnd:"开始日期必须要大于结束日期!";
  }catch(AssertionError err)
  {
   System.out.println(err.getMessage());
  }
  return !begBinggerEnd;
 }
    
    /**
     * 计算两个日期相隔的天数
     * @param startDate, endDate
     * @return The apart days  
     */
    public int SumDays(String startDate,String endDate ){
  int sumDays=0;
        int tempBeginYear =  getYear(startDate);
        int tempEndYear =  getYear(endDate);         
      if(DateCompare(startDate,endDate))
      {      
       return -1;
      }
      else if(tempBeginYear==tempEndYear)
      {              
        sumDays = pastDays(endDate)-pastDays(startDate);                  
       }
       else 
       {         
        for(int i = getYear(startDate)+1;i<getYear(endDate);i++)
        {
              if(IsLeapYearFromYear(i))
               sumDays+=366;
              else
               sumDays+=365;                        
        }
        sumDays+=SurplusDay(startDate);
        sumDays+=pastDays(endDate);         
       }                   
     return sumDays;     
 }
  /**
   * 从日期中获取年
   * @param date
   * @return year
   */
 private int getYear(String  date)
 {
   return Integer.valueOf(date)/10000;
 }
 /**
  * 从日期中获取月
  * @param date
  * @return  month
  */
 private Integer getMonth(String  date)
 {
    return Integer.valueOf(date)%10000/100;
 }  
 /**
  * 从日期中获取日
  * @param date
  * @return days
  */
 private Integer getDay(String date){
    return  Integer.valueOf(date)%10000%100;
 }
 
 /**
  * 判断某个日期是否是闰年
  * @param Date
  * @return boolean
  */
 private boolean isLeapYear(String Date){
  if((getYear(Date)%4==0&&getYear(Date)%100!=0)||getYear(Date)%400==0){  
   return true;
   }
  else{
   return false;
  } 
 }
 /**
  * 判断某年是否是闰年 
  * @param Date
  * @return boolean
  */
 private boolean IsLeapYearFromYear(int year){
  if((year%4==0&&year%100!=0)||year%400==0){
   return true;
  }
  else{ 
   return false;
  }   
 }  
 /**
  * 计算某日期到该年开始的相隔天数
  * @param Date
  * @return 相隔天数
  */
 private int pastDays(String Date){
  int tempSumDays=0;
  if(isLeapYear(Date)){   
   for(int i=1;i<getMonth(Date);i++){
    switch(i){
     case 1:
         tempSumDays+=31;
         break;
     case 2:
      tempSumDays+=29;
      break;
     case 3:
      tempSumDays+=31;
      break;
     case 4:
      tempSumDays+=30;
      break;
     case 5:
      tempSumDays+=31;
      break;
     case 6:
      tempSumDays+=30;
      break;
     case 7:
      tempSumDays+=31;
      break;
     case 8:
      tempSumDays+=31;
      break;
     case 9:
      tempSumDays+=30;
      break;
     case 10:
      tempSumDays+=31;
      break;
     case 11:
      tempSumDays+=30;
      break;
    }
   }
   tempSumDays += getDay(Date);
   return tempSumDays;
  }
  else{
   for(int i=1;i<getMonth(Date);i++){   
    switch(i){
    case 1:
        tempSumDays+=31;
        break;
    case 2:
     tempSumDays+=28;
     break;
    case 3:
     tempSumDays+=31;
     break;
    case 4:
     tempSumDays+=30;
     break;
    case 5:
     tempSumDays+=31;
     break;
    case 6:
     tempSumDays+=30;
     break;
    case 7:
     tempSumDays+=31;
     break;
    case 8:
     tempSumDays+=31;
     break;
    case 9:
     tempSumDays+=30;
     break;
    case 10:
     tempSumDays+=31;
     break;
    case 11:
     tempSumDays+=30;
     break;     
    }
   }
   tempSumDays += getDay(Date);
   return tempSumDays;
  }
 
 }
 /**
  * 计算某日期到该年的结束的相隔天数
  * @param Date
  * @return 相隔天数
  */
 private int SurplusDay(String Date){
 
    if(isLeapYear(Date)){
     return 366-pastDays(Date);
    }
    else{
     return 365-pastDays(Date);
    }
 }
 
}
