package com.hexaware.fastx.service;


import com.hexaware.fastx.entity.Bus;

import java.util.List;

public interface BusService {
    Bus addBus(Bus bus);
    Bus getBusById(Long id);
    List<Bus> getAllBuses();
    void deleteBus(Long id);
}

