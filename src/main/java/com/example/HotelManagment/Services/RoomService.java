package com.example.HotelManagment.Services;


import com.example.HotelManagment.Model.Room;
import com.example.HotelManagment.Repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> findRoomById(int id) {
        return roomRepository.findById(id);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(int id, Room newRoom) {
        return roomRepository.findById(id)
                .map(room -> {
                    room.setRoomNumber(newRoom.getRoomNumber());
                    room.setRoomType(newRoom.getRoomType());
                    room.setFloorNumber(newRoom.getFloorNumber());
                    room.setStatus(newRoom.getStatus());
                    room.setPrice(newRoom.getPrice());
                    return roomRepository.save(room);
                }).orElseGet(() -> {
                    newRoom.setRoomId(id);
                    return roomRepository.save(newRoom);
                });
    }

    public void deleteRoom(int id) {
        roomRepository.deleteById(id);
    }
}
