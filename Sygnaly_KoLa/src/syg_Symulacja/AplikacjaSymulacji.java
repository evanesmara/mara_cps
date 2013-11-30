package syg_Symulacja;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import org.jdesktop.application.Application;

import org.jdesktop.application.SingleFrameApplication;

import syg_package01.AplikacjaGlowna;
import syg_package01.PanelFiltracja;
import syg_package01.Sygnal;

import examples.StatusBar;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * 
 */
@SuppressWarnings("serial")
public class AplikacjaSymulacji extends JFrame {

	protected Properties sym_properties = new Properties();
	private JPanel opcje_Czujnik;
	private JLabel lbl_ob_jednCzasowa;
	private JLabel jLabel1;
	private JPanel opcje_Obiekt;
	private JPanel opcje_Nawigacja;
	private JLabel lbl_ob_predkosc;
	private JLabel lbl_obiekt;
	private JLabel lbl_czujnik;
	private JButton btn_nawigacja;
	private JFormattedTextField txt_obiekt;
	private JFormattedTextField txt_czujnik;
	public UrzadzenieSprawdzajace radar;
	public ObiektWRuchu obiekt;
	public PanelFiltracja zrodlo;

	public AplikacjaSymulacji(PanelFiltracja _source) {
		this.zrodlo = _source;
	}

	public ObiektWRuchu getObiekt() {
		if (this.obiekt == null) {
			this.obiekt = new ObiektWRuchu();
		}
		this.obiekt.opoznienieProbek = Integer.parseInt(""
				+ ((JFormattedTextField) this.opcje_Obiekt.getComponent(3)).getValue());
		System.out.println("Opóźnienie próbek: " + this.obiekt.opoznienieProbek);
		return obiekt;
	}

	public void setObiekt(ObiektWRuchu obiekt) {
		this.obiekt = obiekt;
	}
	
	public void setWynik (double _wynik)
	{
		((JFormattedTextField) this.opcje_Obiekt.getComponent(7)).setValue(_wynik);
	}

	public double getJednostkaCzasowa() {
		System.out.println("Jednostka czasowa: " + ((JFormattedTextField) this.opcje_Obiekt.getComponent(1)).getValue());
		return Double.parseDouble(""
				+ ((JFormattedTextField) this.opcje_Obiekt.getComponent(1)).getValue());
	}

	public String getProperty(String _propertyName) {

		try {
			sym_properties.load(new FileInputStream(
					"src/syg_Symulacja/resources/AplikacjaSymulacji.properties"));
		} catch (IOException e) {
		}

		return sym_properties.getProperty(_propertyName);
	}

	public void setProperty(String _propertyName, String _propertyValue) {

		try {
			sym_properties.load(new FileInputStream(
					"src/syg_Symulacja/resources/AplikacjaSymulacji.properties"));
		} catch (IOException e) {
		}

		sym_properties.setProperty(_propertyName, _propertyValue);
	}

	protected void setPropertiesAll() {
		this.setTitle(getProperty("Application.title"));
	}

	protected JFormattedTextField ustawTextField(JPanel _panel, JFormattedTextField _tf,
			int _lpKolumny, int _wyrowanie, boolean _czyInt) {

		if (_czyInt) {
			_tf = new JFormattedTextField(Integer.TYPE);
			_tf.setValue(new Integer(0));
		} else {
			_tf = new JFormattedTextField(Double.TYPE);
			_tf.setValue(new Double(0.00));
		}
		_tf.setColumns(5);
		_tf.setEditable(true);
		_panel.add(_tf, new GridBagConstraints(_lpKolumny, 1, 1, 1, 0.0, 0.0, _wyrowanie,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		_tf.setMinimumSize(new Dimension(120, 20));
		_tf.setMaximumSize(new Dimension(120, 20));
		_tf.setName(_tf.toString());

		return _tf;
	}

	public Nadajnik pobierzDaneOdUzytkownika(Nadajnik _nadajnik) {
		if (_nadajnik != null) {
			return _nadajnik;
		} else {
			System.out.println("Brak nadajnika w: AplikacjaSymulacji:pobierzDaneOdUzytkownika");
			return null;
		}
	}

	protected void startup() {

		String[] labels_grupyOpcji = new String[] { "OBIEKT", "CZUJNIK", "NAWIGACJA" };
		String[] labels_obiekt = new String[] { "<html>Jednostka<br>czasowa<br>symulatora [s]</html>",
				"<html>Prędkość<br>obiektu<br>(opóźnienie odbioru) [lp]</html>",
				"<html>Prędkość<br>sygnału<br>w ośrodku [m/s]</html>",
				"<html><b>Obliczona<br>chwilowa<br>odległość</b> [m]</html>" };
		String[] labels_czujnik = new String[] { "<html>Okres<br>sygnału</html>",
				"<html>Częstotliwość<br>próbkowania</html>", "<html>Długość<br>buforów</html>",
				"<html>Okres<br>raportowania</html>" };
		String[] labels_nawigacja = new String[] { "Start"/*, "Pauza", "Stop", "Wyjście" */};
		String[] labels_stylOpen = new String[] { "<html><div style='color: #999966;'>" };
		String[] labels_stylClose = new String[] { "</div></html>" };

		int nrTxt = 0;

		this.setPropertiesAll();
		this.setSize(600, 400);
		this.setBounds(400, 100, getSize().width, getSize().height);

		BoxLayout thisLayout = new BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS);
		getContentPane().setLayout(thisLayout);

		GridBagLayout opcje_ObiektLayout = new GridBagLayout();
		opcje_ObiektLayout.columnWeights = new double[] { 0, 0, 0, 0 };
		opcje_ObiektLayout.columnWidths = new int[] { 130, 130, 130, 130 };
		opcje_ObiektLayout.rowWeights = new double[] { 0, 0 };
		opcje_ObiektLayout.rowHeights = new int[] { 20, 20 };

		setVisible(true);

		nrTxt = 0;
		{
			opcje_Obiekt = new JPanel();
			opcje_Obiekt.setLayout(opcje_ObiektLayout);
			getContentPane().add(opcje_Obiekt);
			opcje_Obiekt.setBorder(new TitledBorder(labels_stylOpen[nrTxt]
					+ labels_grupyOpcji[nrTxt] + labels_stylClose[nrTxt]));
			for (nrTxt = 0; nrTxt < labels_obiekt.length; ++nrTxt) {
				{
					lbl_obiekt = new JLabel();
					opcje_Obiekt.add(lbl_obiekt, new GridBagConstraints(nrTxt, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.WEST, GridBagConstraints.NONE,
							new Insets(5, 5, 5, 5), 0, 0));
					lbl_obiekt.setName("lbl_obiekt_" + nrTxt);
					lbl_obiekt.setText(labels_obiekt[nrTxt]);
				}
				{
					txt_obiekt = ustawTextField(opcje_Obiekt, txt_obiekt, nrTxt,
							GridBagConstraints.WEST, false);
					if (nrTxt == 0)
						txt_obiekt.setValue(1000);
					if (nrTxt == 1)
						txt_obiekt.setValue(100);
					if (nrTxt == 2)
						txt_obiekt.setValue(299792458);
					if (nrTxt > 1)
						txt_obiekt.setEnabled(false);
				}
			}
		}
		nrTxt = 0;
		{
			opcje_Czujnik = new JPanel();
			opcje_Czujnik.setLayout(opcje_ObiektLayout);
			getContentPane().add(opcje_Czujnik);
			opcje_Czujnik.setBorder(new TitledBorder(labels_stylOpen[nrTxt]
					+ labels_grupyOpcji[nrTxt + 1] + labels_stylClose[nrTxt]));
			for (nrTxt = 0; nrTxt < labels_czujnik.length; ++nrTxt) {
				{
					lbl_czujnik = new JLabel();
					opcje_Czujnik.add(lbl_czujnik, new GridBagConstraints(nrTxt, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.WEST, GridBagConstraints.NONE,
							new Insets(5, 5, 5, 5), 0, 0));
					lbl_czujnik.setName("lbl_czujnik" + nrTxt);
					lbl_czujnik.setText(labels_czujnik[nrTxt]);
				}
				{
					txt_czujnik = ustawTextField(opcje_Czujnik, txt_czujnik, nrTxt,
							GridBagConstraints.WEST, false);
					if (nrTxt == 0)
						txt_czujnik.setValue(((PanelFiltracja)this.zrodlo).getSygnalFiltrowany().getd());
					if (nrTxt == 1)
						txt_czujnik.setValue(((PanelFiltracja)this.zrodlo).getSygnalFiltrowany().getkrok());
					if (nrTxt == 2)
						txt_czujnik.setValue(((PanelFiltracja)this.zrodlo).getSygnalFiltrowany().punktyNaWYkresie.getItemCount()/10);
					if (nrTxt == 3)
						txt_czujnik.setValue(((PanelFiltracja)this.zrodlo).getSygnalFiltrowany().getd());
					if (nrTxt != 2) txt_czujnik.setEditable(false);
				}
			}
		}
		nrTxt = 0;
		{
			opcje_Nawigacja = new JPanel();
			opcje_Nawigacja.setLayout(opcje_ObiektLayout);
			getContentPane().add(opcje_Nawigacja);
			opcje_Nawigacja.setBorder(new TitledBorder(labels_stylOpen[nrTxt]
					+ labels_grupyOpcji[nrTxt + 2] + labels_stylClose[nrTxt]));
			for (nrTxt = 0; nrTxt < labels_nawigacja.length; ++nrTxt) {
				btn_nawigacja = new JButton();
				opcje_Nawigacja.add(btn_nawigacja, new GridBagConstraints(nrTxt, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5,
								5), 0, 0));
				btn_nawigacja.setName("lbl_czujnik" + nrTxt);
				btn_nawigacja.setText(labels_nawigacja[nrTxt]);
				if (nrTxt == 0) {
					btn_nawigacja
							.addActionListener(new Listener_SymRozpocznij(this, btn_nawigacja));
				}
				
			}
		}

		Application.getInstance().getContext().getResourceMap(getClass())
				.injectComponents(getContentPane());
	}
	
	public void przyciskStop ()
	{
		JButton btn = (JButton)(opcje_Nawigacja.getComponent(2));
		while (btn.getActionListeners() != null && btn.getActionListeners().length > 0)
			btn.removeActionListener(btn.getActionListeners()[btn.getActionListeners().length - 1]);
		btn.addActionListener(new Listener_SymZakoncz(this, btn_nawigacja));
	}

	//
	// public static void main(String[] args) {
	// launch(AplikacjaGlowna.class, args);
	//
	// }

	public void setSygnalNadawany(Sygnal _sygnalDoKorelacji) {
		if (_sygnalDoKorelacji != null) {
			if (this.radar == null)
				this.radar = new UrzadzenieSprawdzajace(this);
			if (this.radar.nadajnik == null)
				this.radar.nadajnik = new Nadajnik(_sygnalDoKorelacji,
						_sygnalDoKorelacji.getkroczek(), 
						Integer.parseInt("" + ((JFormattedTextField) this.opcje_Czujnik.getComponent(5)).getValue())
			, this.getJednostkaCzasowa(), false);
		} else {
			System.out
					.println("Nie można pobrać sygnału do korelacji w: AplikacjaSymulacji:setSygnalNadawany");
		}
	}
}
