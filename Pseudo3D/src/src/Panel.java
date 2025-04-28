package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

class Panel extends JPanel implements KeyListener{
	boolean U=false, D=false, R=false, L=false, RC=false, RA=false;
	int w,h;
	int fps = 20;
	float px=260, py=250;
	int raynum = 100;
	float step=0.5f;
	float dirx = 1, diry = 0.7f;
	float a = 0;
	float k = 0.02f;
	float fov = (float) (Math.PI/2);
	int chosen = 0;
	int[][] map =  {{1,1,1,1,1,1,1,1,1},
					{1,0,0,0,0,0,0,1,1},
					{1,1,1,0,1,1,0,0,1},
					{1,0,0,0,0,1,0,0,1},
					{1,1,0,0,0,1,1,0,1},
					{1,0,1,0,0,0,1,1,1},
					{1,0,1,0,0,0,1,1,1},
					{1,0,1,0,0,0,1,1,1},
					{1,1,1,1,1,1,1,1,1}};
	int bw;
	Timer t = new Timer(1000/fps, new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			repaint();
			tick();
		}
		
	});
	
	Panel(int w, int h){
		this.w = w;
		this.h = h;
		bw = w/(2*map.length);
		this.setBackground(new Color(10));
	}
	
	public void paintComponent(Graphics g) {
		t.start();
		super.paintComponent(g);
		
		g.setColor(Color.YELLOW);
		for(int i=0; i < map.length; i++) {
			for(int j=0; j < map.length; j++) {
				if(map[i][j]==1) g.fillRect(j*bw, i*bw, bw, bw);
			}
		}
		g.setColor(Color.WHITE);
		g.fillOval((int)px-3, (int)py-3, 7, 7);
		dirx = (float) Math.cos(a);
		diry = (float) Math.sin(a);
		for(int i= -1*raynum/2; i<(raynum+1)/2; i++) {
			float rayx=px, rayy=py;
			int counter = 0;
			while(map[(int)(rayy/bw)][(int)(rayx/bw)] != 1){
				rayx += step*Math.cos(a+i*fov/raynum);
				rayy += step*Math.sin(a+i*fov/raynum);
				counter++;
			}
			g.setColor(Color.RED);
			if(i==chosen) {
				g.setColor(Color.GREEN);
			}
			g.drawLine((int)px, (int)py, (int)rayx, (int)rayy);
			g.setColor(new Color((int)(255/(1+k*step*counter)),(int)(255/(1+k*step*counter)),0));
			float columnheight = (float) (h/(k*step*counter*Math.cos(i*fov/raynum)));
			g.fillRect(w*3/4+i*500/raynum,(int)(h/2-columnheight/2),500/raynum,(int)(columnheight));
			
		}
		
		g.setColor(Color.GREEN);
		g.drawRect(w*3/4+chosen*500/raynum, 0, 500/raynum,500);
		if(U) {
			px += Math.cos(a)*5;
			py += Math.sin(a)*5;
		}
		if(R) a+=0.09;
		if(L) a-=0.09;
		if(D) {
			px -= Math.cos(a)*5;
			py -= Math.sin(a)*5;
		}
	}
	public void tick() {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_W) U=true;
		if (e.getKeyCode()==KeyEvent.VK_S) D=true; 
		else if (e.getKeyCode()==KeyEvent.VK_D) R=true; 
		else if (e.getKeyCode()==KeyEvent.VK_A) L=true; 
		else if (e.getKeyCode()==KeyEvent.VK_K) RA=true; 
		else if (e.getKeyCode()==KeyEvent.VK_L) RC=true; 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_W) U=false;
		else if (e.getKeyCode()==KeyEvent.VK_S) D=false; 
		else if (e.getKeyCode()==KeyEvent.VK_D) R=false; 
		else if (e.getKeyCode()==KeyEvent.VK_A) L=false; 
		else if (e.getKeyCode()==KeyEvent.VK_K) RA=false; 
		else if (e.getKeyCode()==KeyEvent.VK_L) RC=false;
	}
}

