import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Date;

import javax.swing.*;

public abstract class Main implements ActionListener {
	
	public static void main(String[] args) throws ParseException {
	new Frame();
	}
}
class Frame extends JFrame implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MoveMouseListener mml = new MoveMouseListener(this);
	
	Frame() throws ParseException {
		setUndecorated(true);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel titleBar = new JPanel();
		titleBar.setLayout(new BorderLayout());
		titleBar.setBounds(0, 0, 800, 30);
		titleBar.setOpaque(false);
		titleBar.addMouseListener(mml);
		titleBar.addMouseListener(this);
		titleBar.addMouseMotionListener(mml);
		
		JButton exitButton = new JButton("×");
		exitButton.setBackground(new Color(244, 162, 97));
		exitButton.setForeground(Color.white);
		exitButton.setFocusable(false);
		exitButton.setFont(new Font("Verdana", Font.PLAIN, 20));
		exitButton.addActionListener(new ActionListener() { @Override
		public void actionPerformed(ActionEvent e) {dispose();}});
		
		JButton minimizeButton = new JButton("—");
		minimizeButton.setForeground(new Color(38, 70, 83));
		minimizeButton.setBackground(Color.white);
		minimizeButton.setFocusable(false);
		minimizeButton.setFont(new Font("Verdana", Font.BOLD, 15));
		minimizeButton.addActionListener(new ActionListener() { @Override
		public void actionPerformed(ActionEvent e) {setExtendedState(JFrame.ICONIFIED);}});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(exitButton, BorderLayout.LINE_END);
		buttonPanel.add(minimizeButton, BorderLayout.LINE_START);
		titleBar.add(buttonPanel, BorderLayout.LINE_END);
		
		JLabel title = new JLabel("Date and Time");
		title.setBounds(330, 5, 300, 30);
		title.setForeground(new Color(38, 70, 83));
		title.setFont(new Font("Arial", Font.BOLD, 20));
		
		add(new Panel());
		add(title);
		add(titleBar);
		pack();
		setVisible(true);
		setSize(800,600);
		getContentPane().setBackground(Color.white);
		setLocationRelativeTo(null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
class Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LocalTime time = LocalTime.now();
	
	Panel() throws ParseException{
		setLayout(null);
		setBounds(0,30,800,570);
		setOpaque(false);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(15,0));
		mainPanel.setBounds(120,150,580,150);
		mainPanel.setOpaque(false);
		
		JPanel secPanel = new JPanel();
		secPanel.setLayout(new BorderLayout());
		secPanel.setOpaque(false);
		secPanel.add(SecClass(), BorderLayout.LINE_START);
		secPanel.add(aClass(), BorderLayout.LINE_END);
		
		mainPanel.add(hourClass(), BorderLayout.LINE_START);
		mainPanel.add(minuteClass(), BorderLayout.CENTER);
		mainPanel.add(secPanel, BorderLayout.LINE_END);
		mainPanel.add(dateClass(), BorderLayout.PAGE_END);
		
		add(mainPanel);
		
	}
	int getHour = time.getHour();
	String hourStr = Integer.toString(getHour);
	JLabel hourLabel;
	String convertedHour;
	
	public JPanel hourClass() throws ParseException {
		
		JPanel hourC = new JPanel();
		
		SimpleDateFormat sdf24 = new SimpleDateFormat("HH");
		Date date = sdf24.parse(hourStr);
		
		hourLabel = new JLabel(convertedHour);
		hourLabel.setFont(new Font("Verdana", Font.BOLD, 100));
		hourLabel.setForeground(new Color(17, 57, 70));
		
		if(getHour<=21) {
			SimpleDateFormat sdf12 = new SimpleDateFormat("0h");
			convertedHour = sdf12.format(date);
			hourLabel.setText(convertedHour);
		} else {
			SimpleDateFormat sdf12 = new SimpleDateFormat("h");
			convertedHour = sdf12.format(date);
			hourLabel.setText(convertedHour);
		}
		
		hourC.setOpaque(false);
		hourC.add(hourLabel);
		return hourC;
	}
	
	int getMinute = time.getMinute();
	String minuteStr = Integer.toString(getMinute);
	JLabel minuteLabel;
	
	public JPanel minuteClass() {
		
		JPanel minuteC = new JPanel();
		
		minuteLabel = new JLabel(minuteStr);
		minuteLabel.setFont(new Font("Verdana", Font.BOLD, 100));
		minuteLabel.setForeground(new Color(17, 57, 70));
		
		if(getMinute<10) {
			String formattedStr = String.format("%02d", getMinute);
			minuteLabel.setText(formattedStr);
		} else minuteLabel.setText(minuteStr);		
		
		minuteC.add(minuteLabel);
		minuteC.setOpaque(false);
		return minuteC;
	}
	int getSecond = time.getSecond();
	String secondStr = Integer.toString(getSecond);
	JLabel secondLabel;
	
	public JPanel SecClass(){
		
		JPanel secC = new JPanel();
		
		secondLabel = new JLabel(secondStr);
		secondLabel.setFont(new Font("Verdana", Font.BOLD, 100));
		secondLabel.setForeground(new Color(17, 57, 70));
		
		if(getSecond<10) {
			String formattedString = String.format("%02d", getSecond);
			secondLabel.setText(formattedString);
		} else secondLabel.setText(secondStr);
		
		Timer timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				
			}
		});
		timer.start();
		
		secC.setOpaque(false);
		secC.add(secondLabel);
		return secC;
	}
	public JPanel aClass(){
		
		JPanel aC = new JPanel();
		
		SimpleDateFormat a = new SimpleDateFormat("a");
		
		Date currentTime = new Date();
		
		String str = a.format(currentTime);
		
		JLabel display = new JLabel(str);
		display.setFont(new Font("Verdana", Font.BOLD, 20));
		display.setForeground(new Color(17, 57, 70));
		
		aC.setOpaque(false);
		aC.setPreferredSize(new Dimension(50,50));
		aC.add(display);
		return aC;
	}
	public JPanel dateClass() {
		JPanel dateC = new JPanel();
		
		LocalDate date = LocalDate.now();
		
		String dayStr = String.valueOf(date.getDayOfMonth());
		String monthStr = date.getMonth().getDisplayName(TextStyle.FULL, getLocale());
		String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, getLocale());
		
		String dateFormat1 = dayOfWeek + ", " + monthStr + " " + dayStr;
		
		JLabel displaytxt = new JLabel(dateFormat1);
		displaytxt.setFont(new Font("Verdana", Font.BOLD, 20));
		displaytxt.setForeground(new Color(244, 162, 97));
		
		dateC.setOpaque(false);
		dateC.add(displaytxt);
		return dateC;
	}
	void update() {
		LocalTime time = LocalTime.now();
		int getMinute = time.getMinute();
		String minuteStr = Integer.toString(getMinute);
		minuteLabel.setText(minuteStr);
		
		if(getMinute<10) {
			String formattedStr = String.format("%02d", getMinute);
			minuteLabel.setText(formattedStr);
		} else minuteLabel.setText(minuteStr);
		
		int getSecond = time.getSecond();
		String secondStr = Integer.toString(getSecond);
		secondLabel.setText(secondStr);
		
		if(getSecond<10) {
			String formattedString = String.format("%02d", getSecond);
			secondLabel.setText(formattedString);
		} else secondLabel.setText(secondStr);
		
	}
	
}
class MoveMouseListener implements MouseListener, MouseMotionListener {
	  Frame target;
	  Point start_drag;
	  Point start_loc;

	  public MoveMouseListener(Frame Frame) {
	    this.target = Frame;
	  }

	  public static JFrame getFrame(Container target) {
	    if (target instanceof JFrame) {
	      return (JFrame) target;
	    }
	    return getFrame(target.getParent());
	  }

	  Point getScreenLocation(MouseEvent e) {
	    Point cursor = e.getPoint();
	    Point target_location = this.target.getLocationOnScreen();
	    return new Point((int) (target_location.getX() + cursor.getX()),
	        (int) (target_location.getY() + cursor.getY()));
	  }

	  public void mouseClicked(MouseEvent e) {
	  }

	  public void mouseEntered(MouseEvent e) {
	  }

	  public void mouseExited(MouseEvent e) {
	  }

	  public void mousePressed(MouseEvent e) {
	    this.start_drag = this.getScreenLocation(e);
	    this.start_loc = MoveMouseListener.getFrame(this.target).getLocation();
	  }

	  public void mouseReleased(MouseEvent e) {
	  }

	  public void mouseDragged(MouseEvent e) {
	    Point current = this.getScreenLocation(e);
	    Point offset = new Point((int) current.getX() - (int) start_drag.getX(),
	        (int) current.getY() - (int) start_drag.getY());
	    JFrame frame = MoveMouseListener.getFrame(target);
	    Point new_location = new Point(
	        (int) (this.start_loc.getX() + offset.getX()), (int) (this.start_loc
	            .getY() + offset.getY()));
	    frame.setLocation(new_location);
	  }

	  public void mouseMoved(MouseEvent e) {
	  }
}