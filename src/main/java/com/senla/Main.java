package com.senla;

import com.senla.dao.CategoryDao;
import com.senla.dao.CommentDao;
import com.senla.dao.PollDao;
import com.senla.dao.PollOptionDao;
import com.senla.dao.PostDao;
import com.senla.dao.PublicationStatusDao;
import com.senla.dao.RoleDao;
import com.senla.dao.SubscriptionDao;
import com.senla.dao.SubscriptionPlanDao;
import com.senla.dao.TagDao;
import com.senla.dao.UserDao;
import com.senla.dao.VoteDao;
import com.senla.dao.imp.CategoryDaoImpl;
import com.senla.dao.imp.CommentDaoImpl;
import com.senla.dao.imp.PollDaoImpl;
import com.senla.dao.imp.PollOptionDaoImpl;
import com.senla.dao.imp.PostDaoImpl;
import com.senla.dao.imp.PublicationStatusDaoImpl;
import com.senla.dao.imp.RoleDaoImpl;
import com.senla.dao.imp.SubscriptionDaoImpl;
import com.senla.dao.imp.SubscriptionPlanDaoImpl;
import com.senla.dao.imp.TagDaoImpl;
import com.senla.dao.imp.UserDaoImpl;
import com.senla.dao.imp.VoteDaoImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        RoleDao roleDao = new RoleDaoImpl();
        SubscriptionPlanDao subscriptionPlanDao = new SubscriptionPlanDaoImpl();
        SubscriptionDao subscriptionDao = new SubscriptionDaoImpl();
        TagDao tagDao = new TagDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();
        PostDao postDao = new PostDaoImpl();
        PublicationStatusDao publicationStatusDao = new PublicationStatusDaoImpl();
        CommentDao commentDao = new CommentDaoImpl();
        PollDao pollDao = new PollDaoImpl();
        PollOptionDao pollOptionDao = new PollOptionDaoImpl();
        VoteDao voteDao = new VoteDaoImpl();
    }
}
