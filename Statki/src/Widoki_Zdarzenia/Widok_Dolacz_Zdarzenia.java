package Widoki_Zdarzenia;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.text.MaskFormatter;

import Widoki_GUI.Widok_Dolacz;

public class Widok_Dolacz_Zdarzenia implements ActionListener, WindowListener{
	
	private Widok_Dolacz widokDolacz;
	
	public Widok_Dolacz_Zdarzenia(Widok_Dolacz _widokDolacz) {
		widokDolacz = _widokDolacz;
		
		widokDolacz.addWindowListener(this);
		
		widokDolacz.btn_RozpocznijRozmieszczanieStatkow.addActionListener(this);
		widokDolacz.btn_WrocDoWyboruKategorii.addActionListener(this);
		widokDolacz.mnI_UstawieniaLokalne.addActionListener(this);
		widokDolacz.mnI_Wyjscie.addActionListener(this);
		widokDolacz.mnI_InstrukcjaObslugi.addActionListener(this);
		widokDolacz.mnI_oGrze.addActionListener(this);
		widokDolacz.mnI_oTworcach.addActionListener(this);
	}
	
	public MaskFormatter stworzFormat(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	        formatter.setPlaceholderCharacter(' ');
	       
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		wyjscie();
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == widokDolacz.btn_WrocDoWyboruKategorii)
			powrtoDoOknaGlownego();
		if(e.getSource() == widokDolacz.btn_RozpocznijRozmieszczanieStatkow)
		{
			
		}
		if(e.getSource() == widokDolacz.mnI_Wyjscie)
			wyjscie();
		if(e.getSource() == widokDolacz.mnI_InstrukcjaObslugi)
		{
			
		}
		if(e.getSource() == widokDolacz.mnI_UstawieniaLokalne)
		{
			widokDolacz.widokGlowny.widokUstawien.widokUstawienZdarzenia.setOknoMacierzyste("OknoDolacz");
			pokazOknoUstawienLokalnych();
		}
		if(e.getSource() == widokDolacz.mnI_oGrze)
		{
			widokDolacz.widokGlowny.widokOpisAplikacji.widokOpisAplikacjiZdarzenia.setOknoMacierzyste("OknoDolacz");
			pokazOknoOpisuAplikacji();
		}
		if(e.getSource() == widokDolacz.mnI_oTworcach)
		{
			widokDolacz.widokGlowny.widokOpisTworcow.widokOpisTworcowZdarzenia.setOknoMacierzyste("OknoDolacz");
			pokazOknoOpisTworcow();
		}
	}
	
	private void wyjscie()
	{
		Object[] opcje = {"Tak","Nie"};
		int odpowiedz = JOptionPane.showOptionDialog(widokDolacz, "Czy na pewno chcesz wyjść z gry?",
		"Statki", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
		null, opcje, opcje[0]); 
		
		if (odpowiedz == JOptionPane.YES_OPTION) {
			System.exit(0);
		} 
	}
	
	private void powrtoDoOknaGlownego()
	{
		widokDolacz.setVisible(false);
		widokDolacz.widokGlowny.setVisible(true);
	}

	private void pokazOknoOpisuAplikacji()
	{
		widokDolacz.setVisible(false);
		widokDolacz.widokGlowny.widokOpisAplikacji.setVisible(true);
	}
	
	private void pokazOknoUstawienLokalnych()
	{
		widokDolacz.setVisible(false);
		widokDolacz.widokGlowny.widokUstawien.setVisible(true);
	}
	
	private void pokazOknoOpisTworcow()
	{
		widokDolacz.setVisible(false);
		widokDolacz.widokGlowny.widokOpisTworcow.setVisible(true);
	}
}