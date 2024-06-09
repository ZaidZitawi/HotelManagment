package com.example.HotelManagment.Services;

import com.example.HotelManagment.DTO.RoomDTO;
import com.example.HotelManagment.Model.Room;
import com.example.HotelManagment.Repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Convert Entity to DTO
    private RoomDTO convertToDTO(Room room) {
        return new RoomDTO(room.getRoomId(),  room.getRoomNumber(), room.getRoomType(), room.getFloorNumber(), room.getStatus(), room.getPrice());
    }

    // Convert DTO to Entity
    private Room convertToEntity(RoomDTO dto) {
        Room room = new Room();
        room.setRoomId(dto.getRoomId());
        room.setRoomNumber(dto.getRoomNumber());
        room.setRoomType(dto.getRoomType());
        room.setStatus(dto.getStatus());
        room.setFloorNumber(dto.getFloorNumber());
        room.setPrice(dto.getPrice());
        return room;
    }

    public RoomDTO saveRoom(RoomDTO dto) {
        Room room = convertToEntity(dto);
        room = roomRepository.save(room);
        return convertToDTO(room);
    }

    public Optional<RoomDTO> getRoomById(int id) {
        return roomRepository.findById(id).map(this::convertToDTO);
    }

    public RoomDTO updateRoom(int id, RoomDTO dto) {
        return roomRepository.findById(id)
                .map(room -> {
                    room.setRoomId(dto.getRoomId());
                    room.setRoomNumber(dto.getRoomNumber());
                    room.setRoomType(dto.getRoomType());
                    room.setStatus(dto.getStatus());
                    room.setFloorNumber(dto.getFloorNumber());
                    room.setPrice(dto.getPrice());
                    return convertToDTO(roomRepository.save(room));
                }).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public void deleteRoom(int id) {
        roomRepository.deleteById(id);
    }

    public List<RoomDTO> checkAvailability() {
        return roomRepository.findAll().stream()
                .filter(room -> room.getStatus().equalsIgnoreCase("Available"))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
