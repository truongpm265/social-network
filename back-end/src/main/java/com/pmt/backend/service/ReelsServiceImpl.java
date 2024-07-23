package com.pmt.backend.service;

import com.pmt.backend.model.Reels;
import com.pmt.backend.model.User;
import com.pmt.backend.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImpl implements ReelsService{

    @Autowired
    private ReelsRepository reelsRepository;
    @Autowired
    private UserService userService;

    @Override
    public Reels createReel(Reels reels, User user) {
        Reels createReels = new Reels();
        createReels.setTitle(reels.getTitle());
        createReels.setUser(user);
        createReels.setVideo(reels.getVideo());
        return reelsRepository.save(createReels);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUserReels(Integer userId) throws Exception {
        userService.findUserById(userId);

        return reelsRepository.findByUserId(userId);
    }
}
