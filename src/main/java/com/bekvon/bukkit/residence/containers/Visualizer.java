package com.bekvon.bukkit.residence.containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.CuboidArea;

import net.Zrips.CMILib.Version.Schedulers.CMITask;

public class Visualizer {
    private Player player;
    private long start;
    private List<CuboidArea> areas = new ArrayList<CuboidArea>();
    private List<CuboidArea> errorAreas = new ArrayList<CuboidArea>();
    private CMITask mainSchduler = null;
    private CMITask errorScheduler = null;
    private CompletableFuture<Void> baseSheduler = null;
    private boolean once = false;
    private int starting = 0;
    private int currentSkip = 0;

    private List<Location> locations = new ArrayList<Location>();
    private List<Location> errorLocations = new ArrayList<Location>();
    private List<Location> locations2 = new ArrayList<Location>();
    private List<Location> errorLocations2 = new ArrayList<Location>();
    private Location loc = null;

    public Visualizer(Player player) {
        this.player = player;
        this.start = System.currentTimeMillis();
    }

    public void cancelAll() {
        if (mainSchduler != null) {
            mainSchduler.cancel();
            mainSchduler = null;
        }
        if (errorScheduler != null) {
            errorScheduler.cancel();
            errorScheduler = null;
        }
        if (baseSheduler != null) {
            baseSheduler.cancel(true);
            baseSheduler = null;
        }
    }

    public boolean isSameLoc() {
        if (loc == null)
            return false;
        if (loc.getWorld() != player.getWorld())
            return false;
        if (!errorAreas.isEmpty() && errorLocations.isEmpty())
            return false;
        if (loc.distance(player.getLocation()) > 1)
            return false;

        return true;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<CuboidArea> getAreas() {
        return areas;
    }

    public void setAreas(ClaimedResidence res) {
        if (res != null)
            this.areas = Arrays.asList(res.getAreaArray());
    }

    public void setAreas(CuboidArea[] areas) {
        this.areas = Arrays.asList(areas);
    }

    public void setAreas(ArrayList<CuboidArea> areas) {
        this.areas = areas;
    }

    public void setAreas(CuboidArea area) {
        areas = new ArrayList<CuboidArea>();
        this.areas.add(area);
    }

    public List<CuboidArea> getErrorAreas() {
        return errorAreas;
    }

    public void setErrorAreas(ClaimedResidence res) {
        if (res != null)
            this.errorAreas = Arrays.asList(res.getAreaArray());
    }

    public void setErrorAreas(CuboidArea[] errorAreas) {
        this.errorAreas = Arrays.asList(errorAreas);
    }

    public void setErrorAreas(ArrayList<CuboidArea> errorAreas) {
        this.errorAreas = errorAreas;
    }

    public void setErrorAreas(CuboidArea errorArea) {
        this.errorAreas.add(errorArea);
    }

    public CMITask getScheduler() {
        return mainSchduler;
    }

    public void setScheduler(CMITask scheduler) {
        this.mainSchduler = scheduler;
    }

    public CMITask getErrorId() {
        return errorScheduler;
    }

    public void setErrorId(CMITask errorId) {
        this.errorScheduler = errorId;
    }

    public boolean isOnce() {
        return once;
    }

    public void setOnce(boolean once) {
        this.once = once;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Location> getLocations2() {
        return locations2;
    }

    public void setLocations2(List<Location> locations) {
        this.locations2 = locations;
    }

    public List<Location> getErrorLocations() {
        return errorLocations;
    }

    public void setErrorLocations(List<Location> errorLocations) {
        this.errorLocations = errorLocations;
    }

    public List<Location> getErrorLocations2() {
        return errorLocations2;
    }

    public void setErrorLocations2(List<Location> errorLocations) {
        this.errorLocations2 = errorLocations;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public int getStarting() {
        return starting;
    }

    public void setStarting(int starting) {
        this.starting = starting;
    }

    public CompletableFuture<Void> getBaseSheduler() {
        return baseSheduler;
    }

    public void setBaseSheduler(CompletableFuture<Void> scheduler) {
        this.baseSheduler = scheduler;
    }

    public int getCurrentSkip() {
        return currentSkip;
    }

    public void setCurrentSkip(int currentSkip) {
        this.currentSkip = currentSkip;
    }

    public void addCurrentSkip() {
        this.currentSkip++;
    }
}
