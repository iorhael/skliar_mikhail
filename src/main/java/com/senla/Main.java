package com.senla;

import com.senla.DAO.CategoryDAO;
import com.senla.DAO.CommentDAO;
import com.senla.DAO.PollDAO;
import com.senla.DAO.PollOptionDAO;
import com.senla.DAO.PostDAO;
import com.senla.DAO.RoleDAO;
import com.senla.DAO.SubscriptionDAO;
import com.senla.DAO.SubscriptionPlanDAO;
import com.senla.DAO.TagDAO;
import com.senla.DAO.UserDAO;
import com.senla.DAO.VoteDAO;
import com.senla.DAO.imp.CategoryDAOImpl;
import com.senla.DAO.imp.CommentDAOImpl;
import com.senla.DAO.imp.PollDAOImpl;
import com.senla.DAO.imp.PollOptionDAOImpl;
import com.senla.DAO.imp.PostDAOImpl;
import com.senla.DAO.imp.RoleDAOImpl;
import com.senla.DAO.imp.SubscriptionDAOImpl;
import com.senla.DAO.imp.SubscriptionPlanDAOImpl;
import com.senla.DAO.imp.TagDAOImpl;
import com.senla.DAO.imp.UserDAOImpl;
import com.senla.DAO.imp.VoteDAOImpl;

public class Main {
    public static void main(String[] args) {
        UserDAO userDao = new UserDAOImpl();
        RoleDAO roleDao = new RoleDAOImpl();
        SubscriptionPlanDAO subscriptionPlanDao = new SubscriptionPlanDAOImpl();
        SubscriptionDAO subscriptionDao = new SubscriptionDAOImpl();
        TagDAO tagDao = new TagDAOImpl();
        CategoryDAO categoryDao = new CategoryDAOImpl();
        PostDAO postDao = new PostDAOImpl();
        CommentDAO commentDao = new CommentDAOImpl();
        PollDAO pollDao = new PollDAOImpl();
        PollOptionDAO pollOptionDao = new PollOptionDAOImpl();
        VoteDAO voteDao = new VoteDAOImpl();
    }
}