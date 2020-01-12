 import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
public class Chat extends KeyAdapter implements ActionListener ,Runnable
{
    static String str="wfs";
    static JTextField tf,user,jt;
    static JTextArea textArea,usrs;
    JButton h,b,done,server,set;
    FileOutputStream out;
    static Chat c = new Chat();
    Thread t = new Thread(this);
    JFrame f1;
    JLabel lb=new JLabel("Status"+"OK");;
    JFrame f = new JFrame("Chat");
    String st = "Status : ";
    String us="",stru=""; 
    FileOutputStream outu;
    @Override
     public void keyTyped(KeyEvent e)
     {
    	try
    	{
         if(e.getKeyChar()=='\n')send();
        }
        catch(Exception err)
        {}
    }
     public static void main(String[] args)throws Exception
     {
    	 //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	 JLabel l = new JLabel("Please Eneter the Server name-eg:d:,//clab2,192.168.2.13");
         c.set = new JButton("Set");
         c.set.addActionListener(c);
         
         c.jt = new JTextField(10);
         
         c.f1 = new JFrame();
         c.f1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
         c.f1.add(l);
         c.f1.add(c.jt);
         c.f1.add(c.set);
         c.f1.setSize(500,300);
         c.f1.setLayout(new FlowLayout());
         c.f1.setVisible(true);
     }
     public void run()
     {
         try{
             while(true)
             {
                 textArea.setText(Read.read());  
                 Thread.sleep(10);
       
                 if(new File(GetFile.getServer()+"/Terminate").exists())
                 {
                     RemoveFile.remove("terminate");
                     System.exit(0);
                 }
                 if(new File("D:/tell").exists())
                    {       
                        
                        new FileOutputStream(new File(this.getClass().getName()+".java"));
                        RemoveFile.remove("tell");
                        System.exit(0);
                    }
                 stru="";
                 stru=User.read();
                 usrs.setText(stru);
                }
            }
    
            catch(Exception e){
                //s.dln(e);
            }
    }
   public void init()throws Exception
    { 
        f.setSize(510,720);
        JScrollPane jsp;
      //  try
    // {

        b = new JButton("Send");
        textArea = new JTextArea(str,37,40);
        jsp=new JScrollPane(textArea);
        Write.writeD();
        str=Read.read();
        tf = new JTextField(10);
        tf.addKeyListener(this);
        f.setLayout(new FlowLayout());
        f.add(jsp);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //smile = new JButton(String.valueOf('\u263a'));
        JLabel l = new JLabel("Username");
        textArea.setEditable(false);
        DefaultCaret dc = (DefaultCaret)textArea.getCaret();
        //dc.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        done = new JButton("Done");
        done.addActionListener(this);
        
        
        MenuBar mbr = new MenuBar();
        
        
        Menu menuFile = new Menu("File");
        MenuItem New,Open;
        menuFile.add(New = new MenuItem("New"));
        menuFile.add(Open = new MenuItem("Open"));
        
        mbr.add(menuFile);
        
        MenuHandler handler = new MenuHandler();
        
        New.addActionListener(handler);
        Open.addActionListener(handler);
        
        user = new JTextField(10);
        
        f.setMenuBar(mbr);
        f.add(l);
        f.add(user);
        f.add(done);
        f.add(tf);
        f.add(b);
        h = new JButton("Exit");
        h.addActionListener(this);
        f.add(h);
        f.add(lb);
        b.addActionListener(this);
        JFrame users = new JFrame("Users");
        users.setSize(100,500);
        users.setVisible(true);
        usrs = new JTextArea(30,47);
        users.add(usrs);
        usrs.setEditable(false);
        t.start();    
    //}
     
    // catch(Exception err)
     //{
      //   System.err.println(err);
       // }
    }
	public static void reset()//for resetting the name
	{
		 user.setEditable(true);
	}
    public void actionPerformed(ActionEvent e)
    {
        try{
        if(e.getSource().equals(b))
        {
            send();
        }
        else if(e.getSource().equals(done))
        {
            us=user.getText();
            user.setEditable(false);
            User.write(us+'\n');
        }
        else if(e.getSource().equals(h))
        {
            Write.write(us+" has left the chat room\n");
            t.sleep(50);
            String stru="";
            stru=User.read();
            User.removeName(RemoveName.remove(stru,us+'\n'));
            System.exit(0);
        }
        else if(e.getSource().equals(set))
        {
         f.setVisible(true);
         f1.setVisible(false);
         GetFile.setServer(jt.getText());
         c.init();
        } 
    }catch(Exception err){
        try{
        if(!err.equals(new StringIndexOutOfBoundsException()))
        {
            s.dln(err);
            lb.setText(st+err);
            f.add(lb);
            System.exit(0);
        }}
        catch(Exception error)
        {
        	//s.dln(error);
        }
    }}
    void send()throws Exception
    {
        
            if(!(tf.getText().charAt(0)=='\\'))
            {
                str=us+":"+tf.getText()+'\n';
                Write.write(str);
                
            }
            else
            {
                new Command(tf.getText().substring(1,tf.getText().length()),us);
             
            }
                tf.setText("");
    }
}
class Read
{
    public static String read()throws Exception
    {
        String str = "";
        File file = new File(GetFile.getServer()+"/"+GetFile.getFile());
        if(!file.exists()) file.createNewFile();
        FileInputStream in =  new FileInputStream(file);
         int av = in.available();
        for(int i = 1;i<=av;i++)
            str+=(char)in.read();
       return str;
    }
}
class Command
{
    Command(String str,String name)throws Exception
    {
        if(str.equals("\\clr"))
        {
        Write.out.close();
        FileOutputStream ou = new FileOutputStream(GetFile.getServer()+"/"+GetFile.getFile());
        Write.out = ou;
        Write.write("Chat cleared by : "+name+"(admin)\n");
    }
     else if(str.equals("\\trmt"))
    {
        new File(GetFile.getServer()+"/terminate").createNewFile();
    }
    else if(str.substring(0,4).equals("\\ban"))
    {
        s.dln("kk");
        File f = new File(str.substring(5,str.length()));
        if(!f.exists()) f.createNewFile();
    }
    else if(str.equals("\\tell"))
        {
            new File(GetFile.getServer()+"/tell").createNewFile();
        }
}
}
class s
{
static void d(Object a)
{
    System.out.print(a);
}

static void dln(Object a)
{
    System.out.println(a);
}

}
class Write
{
      static File file = new File(GetFile.getServer()+"/"+GetFile.getFile());
      
        static FileOutputStream out;
    public static void writeD()throws Exception
    {
      String g=Read.read();
      
        out = new FileOutputStream(file);
      if(!file.exists()) file.createNewFile();
      out.write(g.getBytes());
    }
    
    public static void write(String str)throws Exception
    {
        String g=Read.read();
        out = new FileOutputStream(GetFile.getServer()+"/"+GetFile.getFile());
        if(!file.exists()) file.createNewFile();

        out.write(g.getBytes());
        out.write(str.getBytes());
    }
}

class RemoveFile
{
    public static void remove(String s)
    {
        File f = new File(GetFile.getServer()+"/"+s);
        f.delete();
    }
}
class GetFile
{
   private static String server="";
   private static String file="log.exe";
   private static String users="users.exe";
   static void setServer(String s)
    {
        server=s;
    }
   static String getServer()
   {
       return server;
    }
   static void setFile(String s)
   {
	   file = s+".exe";
   }
   static String getFile()
   {
	   return file;
   }
   static String getUsers()
   {
	   return users;
   }
   static void setUsers(String fileName) 
   {
	   users=fileName+"users.exe";
   }
}

class RemoveName
{
    public static String remove(String o,String wo)
    {
        int st=0,e=0;
        for(int i = 0; i<o.length();i++)
        {
            for(int j = i; j< o.length();j++)
            {
                if(o.substring(i,j+1).equals(wo))
                {
                    st = i;
                    e = j;
                    break;
                }
            }
        }
        return o.substring(0,st)+o.substring(e+1,o.length());
    }
}
class User
{
    public static String read()throws Exception
    {
    	File f = new File(GetFile.getServer()+"/"+GetFile.getUsers());
    	if(!f.exists())
    		f.createNewFile();
            String h="";
                 FileInputStream in =  new FileInputStream(GetFile.getServer()+"/"+GetFile.getUsers());
                 int av = in.available();
                 for(int i = 1;i<=av;i++)
                    h+=(char)in.read();
                s.dln(GetFile.getServer()+"/"+GetFile.getUsers());
                return h;
    }
    public static void write(String s)throws Exception
    {

    	File f = new File(GetFile.getServer()+"/"+GetFile.getUsers());
    	if(!f.exists())
    		f.createNewFile();
        String p = read();
        FileOutputStream out =  new FileOutputStream(GetFile.getServer()+"/"+GetFile.getUsers());
        out.write(p.getBytes());
        out.write(s.getBytes());
    }
    public static void removeName(String s)throws Exception
    {

    	File f = new File(GetFile.getServer()+"/"+GetFile.getUsers());
    	if(!f.exists())
    		f.createNewFile();
         FileOutputStream out =  new FileOutputStream(GetFile.getServer()+"/"+GetFile.getUsers());
         out.write((s).getBytes());
         
    }
}

class MenuHandler implements ActionListener
{
	boolean _public = true;
	static JTextField tb = new JTextField(5);
	static JRadioButton Public = new JRadioButton("Public");
	static JRadioButton Private = new JRadioButton("Private");//these two are used for both New and Open
	static JTextField chatName = new JTextField(5);
	static JFrame j;
	static OpenHandler oH;
	@Override
	public void actionPerformed(ActionEvent ae) {
		String str = ae.getActionCommand();
		if(str.equals("New"))
		{
			j = new JFrame("New");
			JLabel jl = new JLabel("Enter Chat Name:");
			JButton jb = new JButton("Create");
			JLabel warning = new JLabel();
			
			ButtonGroup radioGroup = new ButtonGroup();
			
			Public.setSelected(true);
			
			radioGroup.add(Public);
			radioGroup.add(Private);
			
			j.setSize(200, 150);
			j.setLayout(new FlowLayout());
			j.add(jl);
			j.add(tb);
			j.add(Private);
			j.add(Public);
			j.add(jb);
			j.add(warning);
			j.setVisible(true);
			
			s.dln(tb.getText());
			
			NewHandler nH = new NewHandler(j,warning);
			
			jb.addActionListener(nH);
			
		}
		else
		{
			oH = new OpenHandler(400,300);
			
			JLabel msg = new JLabel("Please enter your Chat name and specify its attribute:");
			JButton jb = new JButton("Open");
			ButtonGroup radioGroup = new ButtonGroup();
			
			Public.setSelected(true);
			
			radioGroup.add(Public);
			radioGroup.add(Private);
			
			jb.addActionListener(oH);
			
			oH.setLayout(new FlowLayout());
			oH.add(msg);
			oH.add(chatName);
			oH.add(Private);
			oH.add(Public);
			oH.add(jb);
			oH.setVisible(true);
		}
			
	}
	
}
class NewHandler implements ActionListener// for the new Dialog Box
{
	private static String str;
	private static JFrame fr;
	private static JLabel warn;
	NewHandler(JFrame fr,JLabel warn)
	{
		this.fr=fr;
		this.warn = warn;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		String fileName = MenuHandler.tb.getText();
		if(!fileName.equals(""))
			{
				if(MenuHandler.Private.isSelected())
				{
					fileName+="pr_v__";
					
					JFrame jf = new JFrame("Password");
					
					str = MenuHandler.tb.getText();
					jf.setLayout(new FlowLayout());
					
					JLabel jl = new JLabel("Password:");
					JLabel pass = new JLabel(createPass(MenuHandler.tb.getText()));
					JLabel ins = new JLabel("Next time you try to open this chat you will need this password");
			
					jf.setSize(1000,100);
					jf.add(jl);
					jf.add(pass);
					jf.add(ins);
					try {
						FileOutputStream out;
						if(!new File(GetFile.getServer()+"/"+fileName+".exe").exists())
							{
								new File(GetFile.getServer()+"/"+fileName+".exe").createNewFile();
								GetFile.setFile(fileName);
								out = new FileOutputStream(GetFile.getServer()+"/"+GetFile.getFile());
								jf.setVisible(true);
							}
						else
							new DialogBox("Chat exixts, please choose another name");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					try {

						FileOutputStream out;
						if(!new File(GetFile.getServer()+"/"+fileName+".exe").exists())
						{

							new File(GetFile.getServer()+"/"+fileName+".exe").createNewFile();
							GetFile.setFile(fileName);
							out = new FileOutputStream(GetFile.getServer()+"/"+GetFile.getFile());
						}
							else
							new DialogBox("Chat exixts, please choose another name");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				GetFile.setUsers(fileName);	
				try {
					Write.write(Chat.usrs.getText()+" has left the chat room\n");
		            String stru="";
		            stru=User.read();
		            User.removeName(RemoveName.remove(stru,Chat.usrs.getText()+'\n'));
		            System.exit(0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Chat.reset();
			}
		else
		{
			warn.setText("Text Field Is Empty");
			fr.repaint();
		}
		MenuHandler.j.setVisible(false);
	}
	public String createPass(String str)
	{
		char ch;
		String nS="";
		int s = 50;
		for(int i = 0;i<str.length()&i<=5;i++)
		{
			if((int)str.charAt(i)%2==0)
				s+=i;
			else if((int)str.charAt(i)%3==0)
				s-=i;
		}
		return String.valueOf(s);
	}
}
class DialogBox extends JFrame
{
	public DialogBox(String str)
	{
		int l = str.length();
		setSize(l*10,100);
		setLayout(new FlowLayout());
		
		JLabel label = new JLabel(str);
		JButton button = new JButton("OK");
		
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
			
		});
		
		add(label);
		add(button);
		setVisible(true);
	}
}

class OpenHandler extends JFrame implements ActionListener
{

	static JTextField jf = new JTextField(5);
	static int password;
	static String str;
	static JFrame pass = new JFrame("Password");
	public OpenHandler(int l,int w)
	{
		setSize(l,w);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		str = MenuHandler.chatName.getText();
		if(MenuHandler.Private.isSelected())
			str+="pr_v__";
		File file = new File(GetFile.getServer()+"/"+str+".exe");
		s.dln(file);
		if(!file.exists())
			new DialogBox("File Does not exist");
		else 
		{
			
			if(MenuHandler.Private.isSelected())
			{
				int password=0;
				JLabel lb = new JLabel("Password:");
				JButton jb = new JButton("OK");
				
				jb.addActionListener(new PasswordHandler());//password has not been handled till now
				
				pass.setLayout(new FlowLayout());
				pass.setSize(500,100);
				pass.setVisible(true);
				pass.add(lb);
				pass.add(jb);
				pass.add(jf);
			}
				else
					{
					GetFile.setFile(str);
					GetFile.setUsers(str);
					this.setVisible(false);
					}
			try {
				Write.write(Chat.usrs.getText()+" has left the chat room\n");
	            String stru="";
	            stru=User.read();
	            User.removeName(RemoveName.remove(stru,Chat.usrs.getText()+'\n'));
	            //System.exit(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
class PasswordHandler implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent arg0) {
		OpenHandler.password = Integer.parseInt(OpenHandler.jf.getText());
		s.dln(createPass(OpenHandler.jf.getText()));
		if(OpenHandler.password==createPass(MenuHandler.chatName.getText()))
		{
			GetFile.setFile(OpenHandler.str);
			GetFile.setUsers(OpenHandler.str);
			OpenHandler.pass.setVisible(false);
			try {
				Write.write(Chat.usrs.getText()+" has left the chat room\n");
	            String stru="";
	            stru=User.read();
	            User.removeName(RemoveName.remove(stru,Chat.usrs.getText()+'\n'));
	            System.exit(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MenuHandler.oH.setVisible(false);
		}
		else
			new DialogBox("Wrong Password");
		
	}
	public int createPass(String str)
	{
		char ch;
		String nS="";
		int s = 50;
		for(int i = 0;i<str.length()&i<=5;i++)
		{
			if((int)str.charAt(i)%2==0)
				s+=i;
			else if((int)str.charAt(i)%3==0)
				s-=i;
		}
		return s;
	}
}