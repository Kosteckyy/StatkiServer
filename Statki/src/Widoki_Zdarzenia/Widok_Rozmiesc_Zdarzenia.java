package Widoki_Zdarzenia;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import Statki.Uzytkownik;
import Widoki_GUI.Widok_Gry;
import Widoki_GUI.Widok_Rozmiesc;

public class Widok_Rozmiesc_Zdarzenia implements ActionListener, WindowListener, ItemListener, MouseListener{
	private Widok_Rozmiesc widokRozmiesc;
	
	private ImageIcon img_poleStatku = new ImageIcon(getClass().getResource("/plansza/plansza220x220/statek.png"));
	private ImageIcon img_zaznaczonePole = new ImageIcon(getClass().getResource("/plansza/plansza220x220/zaznaczonePole.png"));
	private ImageIcon img_zwyklePole = new ImageIcon(getClass().getResource("/plansza/plansza220x220/pole.png"));
	private ImageIcon img_poleZablokowane = new ImageIcon(getClass().getResource("/plansza/plansza220x220/poleZablokowane.png"));
	
	public Widok_Rozmiesc_Zdarzenia(Widok_Rozmiesc _widokRozmiesc) {
		widokRozmiesc = _widokRozmiesc;
		
		widokRozmiesc.addWindowListener(this);
		
		widokRozmiesc.btn_RozmiescLosowo.addActionListener(this);
		widokRozmiesc.btn_RozpocznijRozgrywke.addActionListener(this);
		widokRozmiesc.btn_WyczyscPlansze.addActionListener(this);
		widokRozmiesc.mnI_InstrukcjaObslugi.addActionListener(this);
		widokRozmiesc.mnI_oGrze.addActionListener(this);
		widokRozmiesc.mnI_oTworcach.addActionListener(this);
		widokRozmiesc.mnI_UstawieniaLokalne.addActionListener(this);
		widokRozmiesc.mnI_Wyjscie.addActionListener(this);
		
		widokRozmiesc.cb_RodzajStatku.addItemListener(this);
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

	public boolean czyRozmieszczonoLosowo;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == widokRozmiesc.mnI_UstawieniaLokalne)
		{
			widokRozmiesc.widokGlowny.widokUstawien.widokUstawienZdarzenia.setOknoMacierzyste("OknoRozmiesc");
			pokazOknoUstawienLokalnych();
		}
		if(e.getSource() == widokRozmiesc.mnI_Wyjscie)
		{
			wyjscie();
		}
		if(e.getSource() == widokRozmiesc.mnI_InstrukcjaObslugi)
		{
			
		}
		if(e.getSource() == widokRozmiesc.mnI_oGrze)
		{
			widokRozmiesc.widokGlowny.widokOpisAplikacji.widokOpisAplikacjiZdarzenia.setOknoMacierzyste("OknoRozmiesc");
			pokazOknoOpisuAplikacji();
		}
		if(e.getSource() == widokRozmiesc.mnI_oTworcach)
		{
			widokRozmiesc.widokGlowny.widokOpisTworcow.widokOpisTworcowZdarzenia.setOknoMacierzyste("OknoRozmiesc");
			pokazOknoOpisTworcow();
		}
		if(e.getSource() == widokRozmiesc.btn_RozpocznijRozgrywke)
		{
			pokazOknoOpisGry();
			
		}
		if(e.getSource() == widokRozmiesc.btn_WyczyscPlansze)
		{
			czyscPlansze();
		}
		if(e.getSource() == widokRozmiesc.btn_RozmiescLosowo)
		{
			widokRozmiesc.uzytkownik.plansza.rozmiescStatkiLosowo();
			przerysujPlansze();
			widokRozmiesc.btn_RozmiescLosowo.setEnabled(false);
			widokRozmiesc.btn_WyczyscPlansze.setEnabled(true);
			
			widokRozmiesc.lblStatek.setVisible(false);
			widokRozmiesc.cb_RodzajStatku.setVisible(false);
			if(widokRozmiesc.cb_Rozmieszczenie.isVisible())
			{
				widokRozmiesc.lblRozmieszczenie.setVisible(false);
				widokRozmiesc.cb_Rozmieszczenie.setVisible(false);
			}
			czyRozmieszczonoLosowo = true;
			widokRozmiesc.btn_RozpocznijRozgrywke.setEnabled(true);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(e.getSource() == widokRozmiesc.cb_RodzajStatku)
		{
			if(widokRozmiesc.cb_RodzajStatku.getSelectedItem().toString().equals("jednomasztowiec"))
			{
				widokRozmiesc.lblRozmieszczenie.setVisible(false);
				widokRozmiesc.cb_Rozmieszczenie.setVisible(false);
			}
			else
			{
				widokRozmiesc.lblRozmieszczenie.setVisible(true);
				widokRozmiesc.cb_Rozmieszczenie.setVisible(true);
			}
		}
	}
	
	int liczbaKlikania = 0;
	boolean czyMoznaKlikac = true;
	@Override
	public void mouseClicked(MouseEvent e) {
		if(czyMoznaKlikac)
		{
			if(!czyRozmieszczonoLosowo)
			{
				liczbaKlikania++;
				
				for(int i = 0;i < 10; i++)
					for(int j = 0;j < 10; j++)
						if(e.getSource() == lb_polaGry[j][i])
						{
							if(!widokRozmiesc.cb_RodzajStatku.getSelectedItem().toString().equals("") && 
									!widokRozmiesc.cb_Rozmieszczenie.getSelectedItem().toString().equals(""))
							{
								if(liczbaKlikania > 0)
									widokRozmiesc.btn_RozmiescLosowo.setEnabled(false);
								widokRozmiesc.uzytkownik.plansza.ustawStatek(widokRozmiesc.cb_RodzajStatku.getSelectedItem().toString(), 
										widokRozmiesc.cb_Rozmieszczenie.getSelectedItem().toString(), j, i);
								ustawListeRozwijana_ROZMIAR();
								przerysujPlansze();
							}
						}
				System.out.print("-----------------------------------------------------------\n");
				//System.out.print("Jednomasztowiec: "+widokRozmiesc.uzytkownik.plansza.getLiczbaStatkow("jednomasztowiec")+"\n");
				for(int i=0;i<10;i++)
				{
					for(int j=0;j<10;j++)
					{
						System.out.print(widokRozmiesc.uzytkownik.plansza.l_polaPlanszy_GRACZ[j][i].getRodzajPola()+" ");
						if(j == 9)
							System.out.println();
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(!czyRozmieszczonoLosowo)
		{
			if(widokRozmiesc.cb_RodzajStatku.getItemCount() > 0)
				for(int i = 0;i < 10; i++)
					for(int j = 0;j < 10; j++)
						if(e.getSource() == lb_polaGry[j][i])
						{
							if(widokRozmiesc.uzytkownik.plansza.sprawdzWspolrzedneUstawianegoStatku(widokRozmiesc.cb_RodzajStatku.getSelectedItem().toString(), 
									widokRozmiesc.cb_Rozmieszczenie.getSelectedItem().toString(), j, i))
							{
								zaznaczMaszty(true, j, i, widokRozmiesc.cb_RodzajStatku, widokRozmiesc.cb_Rozmieszczenie);
							}
						}
		}	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(!czyRozmieszczonoLosowo)
		{
			if(widokRozmiesc.cb_RodzajStatku.getItemCount() > 0)
				for(int i = 0;i < 10; i++)
					for(int j = 0;j < 10; j++)
						if(e.getSource() == lb_polaGry[j][i])
							if(widokRozmiesc.uzytkownik.plansza.sprawdzWspolrzedneUstawianegoStatku(widokRozmiesc.cb_RodzajStatku.getSelectedItem().toString(), 
									widokRozmiesc.cb_Rozmieszczenie.getSelectedItem().toString(), j, i))
							{
								zaznaczMaszty(false ,j, i, widokRozmiesc.cb_RodzajStatku, widokRozmiesc.cb_Rozmieszczenie);
							}
		}
					
	}
	
	public void czyscPlansze()
	{
		widokRozmiesc.uzytkownik.plansza.czyscPlansze();
		przerysujPlansze();
		widokRozmiesc.btn_RozmiescLosowo.setEnabled(true);
		widokRozmiesc.cb_RodzajStatku.setModel(new DefaultComboBoxModel(new String[] {"jednomasztowiec", "dwumasztowiec", "trójmasztowiec", "czteromasztowiec"}));
		widokRozmiesc.btn_RozmiescLosowo.setEnabled(true);
		liczbaKlikania = 0;
		czyRozmieszczonoLosowo = false;
		widokRozmiesc.lblStatek.setVisible(true);
		widokRozmiesc.cb_RodzajStatku.setVisible(true);
		if(!widokRozmiesc.cb_RodzajStatku.getSelectedItem().toString().equals("jednomasztowiec"))
		{
			widokRozmiesc.lblRozmieszczenie.setVisible(true);
			widokRozmiesc.cb_Rozmieszczenie.setVisible(true);
		}
		else
		{
			widokRozmiesc.lblRozmieszczenie.setVisible(false);
			widokRozmiesc.cb_Rozmieszczenie.setVisible(false);
		}
		
		widokRozmiesc.btn_RozpocznijRozgrywke.setEnabled(false);
		czyMoznaKlikac = true;
	}
	
	public void ustawListeRozwijana_ROZMIAR()
	{
		String s_rozmiaryStatkow[] = {"jednomasztowiec", "dwumasztowiec", "trójmasztowiec", "czteromasztowiec"};
		int lStatkow[] = new int[4];
		boolean czyUstawionoDoKoncaStatek = false;
		
		lStatkow = widokRozmiesc.uzytkownik.plansza.getLiczbaStatkow();
		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

		for(int i = 0; i < 5; i++)
		{
			if(i > 0)
				if(lStatkow[i-1] < 5-i)
					model.addElement(s_rozmiaryStatkow[i-1]);
		}
		widokRozmiesc.cb_RodzajStatku.setModel(model);
		if(widokRozmiesc.cb_RodzajStatku.getItemCount() > 1)
			widokRozmiesc.cb_RodzajStatku.setSelectedItem(1);
		
		boolean czyJestJednomasztowiec = false;
		
		if(widokRozmiesc.cb_RodzajStatku.getItemCount() > 0)
		{
			widokRozmiesc.lblStatek.setVisible(true);
			widokRozmiesc.cb_RodzajStatku.setVisible(true);
			
			if(!widokRozmiesc.cb_RodzajStatku.getSelectedItem().toString().equals("jednomasztowiec"))
			{
				widokRozmiesc.lblRozmieszczenie.setVisible(true);
				widokRozmiesc.cb_Rozmieszczenie.setVisible(true);
			}
		}
		else
		{
			widokRozmiesc.lblStatek.setVisible(false);
			widokRozmiesc.cb_RodzajStatku.setVisible(false);
			widokRozmiesc.lblRozmieszczenie.setVisible(false);
			widokRozmiesc.cb_Rozmieszczenie.setVisible(false);
			widokRozmiesc.btn_RozpocznijRozgrywke.setEnabled(true);
			czyMoznaKlikac = false;
		}
	}
	
	public Uzytkownik getUzytkownik()
	{
		return widokRozmiesc.uzytkownik;
	}
	
	int liczbaPrzerysowaniaPlanszy = 0;
	public void przerysujPlansze()
	{
		liczbaPrzerysowaniaPlanszy++;
			for(int k=0;k<10;k++)
				for(int l=0;l<10;l++)
				{
					if(liczbaPrzerysowaniaPlanszy > 1)
					{
						if(widokRozmiesc.uzytkownik.plansza.l_polaPlanszy_GRACZ[l][k].getRodzajPola().equals("1"))
						{
							//lb_polaGry[l][k].setBackground(Color.BLUE);
							lb_polaGry[l][k].setIcon(img_poleStatku);
						}
						else if(widokRozmiesc.uzytkownik.plansza.l_polaPlanszy_GRACZ[l][k].getRodzajPola().equals("X"))
						{
							//lb_polaGry[l][k].setBackground(Color.RED);
							lb_polaGry[l][k].setIcon(img_poleZablokowane);
						}
						else
						{
							//lb_polaGry[l][k].setBackground(new Color(240,240,240));
							lb_polaGry[l][k].setIcon(img_zwyklePole);
						}
					}
					else
						lb_polaGry[l][k].setIcon(img_zwyklePole);
				}
	}
	
	private void wyjscie()
	{
		Object[] opcje = {"Tak","Nie"};
		int odpowiedz = JOptionPane.showOptionDialog(widokRozmiesc, "Czy na pewno chcesz wyjść z gry?",
		"Statki", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
		null, opcje, opcje[0]); 
		
		if (odpowiedz == JOptionPane.YES_OPTION) {
			System.exit(0);
		} 
	}
	
	private void pokazOknoOpisuAplikacji()
	{
		widokRozmiesc.setVisible(false);
		widokRozmiesc.widokGlowny.widokOpisAplikacji.setVisible(true);
	}
	
	private void pokazOknoUstawienLokalnych()
	{
		widokRozmiesc.setVisible(false);
		widokRozmiesc.widokGlowny.widokUstawien.setVisible(true);
	}
	
	private void pokazOknoOpisTworcow()
	{
		widokRozmiesc.setVisible(false);
		widokRozmiesc.widokGlowny.widokOpisTworcow.setVisible(true);
	}
	
	private void pokazOknoOpisGry()
	{
		Widok_Gry widokGry = new Widok_Gry(widokRozmiesc.widokGlowny, widokRozmiesc.host, widokRozmiesc.gracz, widokRozmiesc.uzytkownik);
		widokRozmiesc.setVisible(false);
		//widokRozmiesc.widokGry.setVisible(true);
		widokGry.lb_AwatarGracza.setText(widokRozmiesc.uzytkownik.plansza.l_polaPlanszy_GRACZ[0][0].getRodzajPola());
		widokGry.setVisible(true);
	}
	
	JLabel lb_polaGry[][];
	public void stworzPolaGry(JPanel _obszarPol, JPanel _obszarLiter, JPanel _obszarCyfr)
	{
		JLabel lb_litery[] = new JLabel[10];
		JLabel lb_cyfry[] = new JLabel[10];
		lb_polaGry = new JLabel[10][10];
		String litery[] = {"A","B","C","D","E","F","G","H","I","J"}; 
		
		int x = 0, y = 0;
		for(int i = 0;i < 10; i++)
		{
			x = 0;
			y+=20;
			for(int j = 0;j < 10; j++)
			{
				x+=20;
				lb_polaGry[j][i] = new JLabel();
				lb_polaGry[j][i].setBounds(x, y, 20, 20);
				//lb_polaGry[j][i].setText(String.valueOf(j)+String.valueOf(i));
				//lb_polaGry[j][i].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
				lb_polaGry[j][i].setOpaque(false);
				lb_polaGry[j][i].addMouseListener(this);
				_obszarPol.add(lb_polaGry[j][i]);
				
				/*lb_cyfry[i] = new JLabel();
				lb_cyfry[i].setBounds(x, 0, 20, 20);
				lb_cyfry[i].setFont(new Font("Verdana", Font.BOLD, 12));
				lb_cyfry[i].setText(String.valueOf(j+1));
				lb_cyfry[i].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 1));
				lb_cyfry[i].setHorizontalTextPosition(SwingConstants.CENTER);
				_obszarCyfr.add(lb_cyfry[i]);*/
			}
			/*lb_litery[i] = new JLabel();
			lb_litery[i].setBounds(3, y, 20, 20);
			lb_litery[i].setFont(new Font("Verdana", Font.BOLD, 12));
			lb_litery[i].setText(litery[i]);
			lb_litery[i].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 1));
			lb_litery[i].setHorizontalTextPosition(SwingConstants.CENTER);
			_obszarLiter.add(lb_litery[i]);*/
		}
	}

	
	public void zaznaczMaszty(boolean _zdarzenie, int _pozXpoczatekStatku, int _pozYpoczatekStatku, JComboBox _rodzajStatku, JComboBox _rozmieszczenie)
	{
		
		String rodzajStatku = _rodzajStatku.getSelectedItem().toString();
		String rozmieszczenie = _rozmieszczenie.getSelectedItem().toString();
		int x = _pozXpoczatekStatku;
		int y = _pozYpoczatekStatku;
		
		if(rodzajStatku.equals("jednomasztowiec"))
		{
			if(_zdarzenie)
			{
				//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				lb_polaGry[x][y].setIcon(img_zaznaczonePole);
			}
			else 
			{
				//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
				lb_polaGry[x][y].setIcon(img_zwyklePole);
			}
		}
		else
			if(rozmieszczenie.equals("pionowo"))
			{
				
				if(rodzajStatku.equals("dwumasztowiec"))
				{
					if(_pozYpoczatekStatku <= 8)
					{
						if(_zdarzenie)
						{
						//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						//lb_polaGry[x][y+1].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						lb_polaGry[x][y].setIcon(img_zaznaczonePole);
						lb_polaGry[x][y+1].setIcon(img_zaznaczonePole);
						}
						else 
						{
							//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							//lb_polaGry[x][y+1].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							lb_polaGry[x][y].setIcon(img_zwyklePole);
							lb_polaGry[x][y+1].setIcon(img_zwyklePole);
						}
					}
				}
				if(rodzajStatku.equals("trójmasztowiec"))
				{
					if(_pozYpoczatekStatku <= 7)
					{
						if(_zdarzenie)
						{
						//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						//lb_polaGry[x][y+1].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						//lb_polaGry[x][y+2].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						lb_polaGry[x][y].setIcon(img_zaznaczonePole);
						lb_polaGry[x][y+1].setIcon(img_zaznaczonePole);
						lb_polaGry[x][y+2].setIcon(img_zaznaczonePole);
						}
						else 
						{
							//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							//lb_polaGry[x][y+1].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							//lb_polaGry[x][y+2].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							lb_polaGry[x][y].setIcon(img_zwyklePole);
							lb_polaGry[x][y+1].setIcon(img_zwyklePole);
							lb_polaGry[x][y+2].setIcon(img_zwyklePole);
						}
					}
				}
				if(rodzajStatku.equals("czteromasztowiec"))
				{
					if(_pozYpoczatekStatku <= 6)
					{
						if(_zdarzenie)
						{
						//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						//lb_polaGry[x][y+1].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						//lb_polaGry[x][y+2].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						//lb_polaGry[x][y+3].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						lb_polaGry[x][y].setIcon(img_zaznaczonePole);
						lb_polaGry[x][y+1].setIcon(img_zaznaczonePole);
						lb_polaGry[x][y+2].setIcon(img_zaznaczonePole);
						lb_polaGry[x][y+3].setIcon(img_zaznaczonePole);
						}
						else 
						{
							//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							//lb_polaGry[x][y+1].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							//lb_polaGry[x][y+2].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							//lb_polaGry[x][y+3].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							lb_polaGry[x][y].setIcon(img_zwyklePole);
							lb_polaGry[x][y+1].setIcon(img_zwyklePole);
							lb_polaGry[x][y+2].setIcon(img_zwyklePole);
							lb_polaGry[x][y+3].setIcon(img_zwyklePole);
						}
					}
				}
			}
			if(rozmieszczenie.equals("poziomo"))
			{
				/*if(rodzajStatku.equals("jednomasztowiec"))
				{
					if(_zdarzenie)
					{
						//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						lb_polaGry[x][y].setIcon(img_zaznaczonePole);
					}
					else 
					{
						//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x][y].setIcon(img_zwyklePole);
					}
				}*/
				if(rodzajStatku.equals("dwumasztowiec"))
				{
					if(_pozXpoczatekStatku <= 8)
					{
						if(_zdarzenie)
						{
						//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						//lb_polaGry[x+1][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						lb_polaGry[x][y].setIcon(img_zaznaczonePole);
						lb_polaGry[x+1][y].setIcon(img_zaznaczonePole);
						}
						else 
						{
							//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							//lb_polaGry[x+1][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							lb_polaGry[x][y].setIcon(img_zwyklePole);
							lb_polaGry[x+1][y].setIcon(img_zwyklePole);
						}
					}
				}
				if(rodzajStatku.equals("trójmasztowiec"))
				{
					if(_pozXpoczatekStatku <= 7)
					{
						if(_zdarzenie)
						{
						//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						//lb_polaGry[x+1][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						//lb_polaGry[x+2][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						lb_polaGry[x][y].setIcon(img_zaznaczonePole);
						lb_polaGry[x+1][y].setIcon(img_zaznaczonePole);
						lb_polaGry[x+2][y].setIcon(img_zaznaczonePole);
						}
						else 
						{
							//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							//lb_polaGry[x+1][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							//lb_polaGry[x+2][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							lb_polaGry[x][y].setIcon(img_zwyklePole);
							lb_polaGry[x+1][y].setIcon(img_zwyklePole);
							lb_polaGry[x+2][y].setIcon(img_zwyklePole);
						}
					}
				}
				if(rodzajStatku.equals("czteromasztowiec"))
				{
					if(_pozXpoczatekStatku <= 6)
					{
						if(_zdarzenie)
						{
						//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						//lb_polaGry[x+1][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						//lb_polaGry[x+2][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						//lb_polaGry[x+3][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
						lb_polaGry[x][y].setIcon(img_zaznaczonePole);
						lb_polaGry[x+1][y].setIcon(img_zaznaczonePole);
						lb_polaGry[x+2][y].setIcon(img_zaznaczonePole);
						lb_polaGry[x+3][y].setIcon(img_zaznaczonePole);
						}
						else 
						{
							//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							//lb_polaGry[x+1][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							//lb_polaGry[x+2][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							//lb_polaGry[x+3][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
							lb_polaGry[x][y].setIcon(img_zwyklePole);
							lb_polaGry[x+1][y].setIcon(img_zwyklePole);
							lb_polaGry[x+2][y].setIcon(img_zwyklePole);
							lb_polaGry[x+3][y].setIcon(img_zwyklePole);
						}
					}
				}
			}
		
		//lb_polaGry[j][i].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		
		//lb_polaGry[j][i].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
	}

	public void zaznaczMasztyNaStale(boolean _zdarzenie, int _pozXpoczatekStatku, int _pozYpoczatekStatku, JComboBox _rodzajStatku, JComboBox _rozmieszczenie)
	{
		String rodzajStatku = _rodzajStatku.getSelectedItem().toString();
		String rozmieszczenie = _rozmieszczenie.getSelectedItem().toString();
		int x = _pozXpoczatekStatku;
		int y = _pozYpoczatekStatku;
		
		if(rozmieszczenie.equals("pionowo"))
		{
			if(rodzajStatku.equals("jednomasztowiec"))
			{
				if(_zdarzenie)
				{
					//lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					lb_polaGry[x][y].setBackground(Color.BLUE);
				}
				else 
				{
					lb_polaGry[x][y].setBackground(Color.RED);
				}
			}
			if(rodzajStatku.equals("dwumasztowiec"))
			{
				if(_pozYpoczatekStatku <= 8)
				{
					if(_zdarzenie)
					{
					lb_polaGry[x][y].setBackground(Color.BLUE);
					lb_polaGry[x][y+1].setBackground(Color.BLUE);
					}
					else 
					{
						lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x][y+1].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
					}
				}
			}
			if(rodzajStatku.equals("trójmasztowiec"))
			{
				if(_pozYpoczatekStatku <= 7)
				{
					if(_zdarzenie)
					{
					lb_polaGry[x][y].setBackground(Color.BLUE);
					lb_polaGry[x][y+1].setBackground(Color.BLUE);
					lb_polaGry[x][y+2].setBackground(Color.BLUE);
					}
					else 
					{
						lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x][y+1].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x][y+2].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
					}
				}
			}
			if(rodzajStatku.equals("czteromasztowiec"))
			{
				if(_pozYpoczatekStatku <= 6)
				{
					if(_zdarzenie)
					{
					lb_polaGry[x][y].setBackground(Color.BLUE);
					lb_polaGry[x][y+1].setBackground(Color.BLUE);
					lb_polaGry[x][y+2].setBackground(Color.BLUE);
					lb_polaGry[x][y+3].setBackground(Color.BLUE);
					}
					else 
					{
						lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x][y+1].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x][y+2].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x][y+3].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
					}
				}
			}
		}
		if(rozmieszczenie.equals("poziomo"))
		{
			if(rodzajStatku.equals("jednomasztowiec"))
			{
				if(_zdarzenie)
				lb_polaGry[x][y].setBackground(Color.BLUE);
				else 
				{
					lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
				}
			}
			if(rodzajStatku.equals("dwumasztowiec"))
			{
				if(_pozXpoczatekStatku <= 8)
				{
					if(_zdarzenie)
					{
					lb_polaGry[x][y].setBackground(Color.BLUE);
					lb_polaGry[x+1][y].setBackground(Color.BLUE);
					}
					else 
					{
						lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x+1][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
					}
				}
			}
			if(rodzajStatku.equals("trójmasztowiec"))
			{
				if(_pozXpoczatekStatku <= 7)
				{
					if(_zdarzenie)
					{
					lb_polaGry[x][y].setBackground(Color.BLUE);
					lb_polaGry[x+1][y].setBackground(Color.BLUE);
					lb_polaGry[x+2][y].setBackground(Color.BLUE);
					}
					else 
					{
						lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x+1][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x+2][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
					}
				}
			}
			if(rodzajStatku.equals("czteromasztowiec"))
			{
				if(_pozXpoczatekStatku <= 6)
				{
					if(_zdarzenie)
					{
					lb_polaGry[x][y].setBackground(Color.BLUE);
					lb_polaGry[x+1][y].setBackground(Color.BLUE);
					lb_polaGry[x+2][y].setBackground(Color.BLUE);
					lb_polaGry[x+3][y].setBackground(Color.BLUE);
					}
					else 
					{
						lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x+1][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x+2][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x+3][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
					}
				}
			}
		}
	}
}
