package com.pmt.backend.service;

import com.pmt.backend.model.Reels;
import com.pmt.backend.model.User;

import java.util.List;

public interface ReelsService {

    public Reels createReel(Reels reels, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUserReels(Integer userId) throws Exception;

}
