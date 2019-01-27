package controller;

import beans.User;
import beans.Seller;
import cstate.CState;
import daofactory.DAO_Factory;
import utils.Pair;
import view.View;
import view.LoginView;

import java.util.ArrayList;

public class Controller {
    private View m_current_view = null;

    public Controller () {
        CState current_state = new CState ();
        m_current_view = new LoginView (current_state);

        while (m_current_view != null)
        {
            m_current_view.Display ();
            m_current_view = m_current_view.GetNextView();
        }

        current_state.GetDAOFactory().deactivateConnection();
    }
}