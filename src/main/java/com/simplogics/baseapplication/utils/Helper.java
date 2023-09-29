package com.simplogics.baseapplication.utils;

import com.simplogics.baseapplication.entity.Event;
import com.simplogics.baseapplication.entity.EventStoreMap;
import com.simplogics.baseapplication.entity.Store;
import com.simplogics.baseapplication.exception.CustException;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static List<Integer> findEventStoreMap(List<EventStoreMap> storeMaps, long esID) throws CustException {
        for(EventStoreMap esm:storeMaps){
            if(esm.getId()==esID) {
                List<Integer> values = new ArrayList<>();
                values.add(esm.getEventId());
                values.add(esm.getStoreId());
                return values;
            }
        }
        throw new CustException("The ID wasn't found",500);
    }

    public static Event findEventByCode(List<Event> events, int eventId) throws CustException {
        for(Event e: events){
            if(e.getEventCode()==eventId){
                return e;
            }
        }
        throw new CustException("No event was found for the corresponding id",500);
    }

    public static Store findStoreByCode(List<Store> stores, int  storeId)throws CustException{
        for(Store s: stores){
            if(s.getStoreCode()==storeId){
                return s;
            }
        }
        throw new CustException("No store was found for the corresponding id",500);
    }
}
