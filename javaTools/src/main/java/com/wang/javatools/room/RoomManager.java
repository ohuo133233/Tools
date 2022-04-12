package com.wang.javatools.room;

import android.app.Application;

import androidx.room.Room;

import java.util.List;

public class RoomManager {
    private AppDatabase database;

    public void initRoom(Application application) {
        database = Room
                .databaseBuilder(application.getApplicationContext(),
                        AppDatabase.class, "database-name")
                .allowMainThreadQueries()
                .build();
    }

    public List<User> query() {
        UserDao userDao = database.userDao();
        return userDao.getAll();
    }

    public void delete(User user) {
        database.userDao().delete(user);
    }

    public void insert(User user) {
        database.userDao().insertAll(user);
    }

    public void inserts(User... users) {
        database.userDao().insertAll(users);
    }

    public void upData(User user) {
        database.userDao().upData(user);
    }

}
