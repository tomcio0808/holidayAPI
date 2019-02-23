package holiday.service.api;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Comparator {
	
	private HolidayApiMultiple firstCountry;
	private HolidayApiMultiple secondCountry;
	
	private Map<String, List<HolidayApiSingle>> firstMap;
	private Map<String, List<HolidayApiSingle>> secondMap;
	private Map<String, List<HolidayApiSingle>> buff;
	private String commonKey;
	
	
	
	public Comparator(HolidayApiMultiple firstCountry, HolidayApiMultiple secondCountry) {
		this.firstMap = firstCountry.getHolidays();
		this.secondMap = secondCountry.getHolidays();
	}
	
	//find common holidays for given countries
	public void findCommon(String date) {
		buff = firstMap;
		buff.keySet().retainAll(secondMap.keySet());
		
		//check if there is common holiday at all
		if(!buff.isEmpty()) {
			//make sure that common keys are sorted in descending order
			TreeMap<String, List<HolidayApiSingle>> sortedCommonMap = new TreeMap<>(buff);	
			
			//find key greater ore equal to given one
			Map.Entry<String, List<HolidayApiSingle>> holiday = sortedCommonMap.ceilingEntry(date);
			//check if there is common holiday after given date
			if(holiday != null)
				commonKey = holiday.getKey();
			else
				commonKey = null;
		} else {
			commonKey = null;
		}
	}
	
	public String getCommonKey() {
		return commonKey;
	}
}
