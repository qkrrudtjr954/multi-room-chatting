package main;

import dto.Guest;
import dto.Room;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Guest guest = new Guest();
        System.out.println("이름은 무엇입니까?");
        guest.name = scanner.next();


        List<Room> roomList = new ArrayList<>();
        Room room0 = new Room("test1");
        roomList.add(room0);
        Room room1 = new Room("test2");
        roomList.add(room1);
        Room room2 = new Room("test3");
        roomList.add(room2);
        Room room3 = new Room("test4");
        roomList.add(room3);
        Room room4 = new Room("test5");
        roomList.add(room4);
        Room room5 = new Room("test6");
        roomList.add(room5);

        Room selectedRoom = null;


        roomList.stream().forEach(room -> System.out.println(room.name));
        System.out.println("어느 방에 입장하시겠습니까? ");
        int i = scanner.nextInt();

        selectedRoom = roomList.get(i);

        if (selectedRoom.guests.add(guest)) {
            selectedRoom.guests.forEach(System.out::println);
            System.out.println("입장했습니다.");
        } else {
            System.out.println("입장할 수 없습니다. ");
        }


        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9000);
        Socket socket = new Socket();


        try {
            socket.connect(address, 10000);

            new ReadThread(socket).start();

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(selectedRoom.name);

            while (true) {
                System.out.print("Msg: ");
                String msg = scanner.next();
                dos.writeUTF(msg);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }

    public static void sendMsg(Socket socket) {

        Scanner scanner = new Scanner(System.in);

        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());


            String msg = scanner.next();

            dos.writeUTF(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
