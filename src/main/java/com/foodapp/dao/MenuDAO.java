package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.Menu;

public interface MenuDAO {

    int addMenu(Menu menu);

    Menu getMenuById(int menuId);

    List<Menu> getMenuByRestaurant(int restaurantId);

    List<Menu> getAllMenu();

    int updateMenu(Menu menu);

    int deleteMenu(int menuId);
}