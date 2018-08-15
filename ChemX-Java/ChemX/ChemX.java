import java.io.*;
import java.lang.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;

class ChemX extends JFrame 
{
    public static JPanel panel_background=new JPanel();
    public static String copyright="           This Software is the result of Developer's Hardwork and Determination.\n\t\tCirculation of any illegal copies of the software without the developer's knowledge is\n                                                     STRICTLY PROHIBITED";
    public static String[] background_m={"sound\\background_1.wav","sound\\background_2.wav","sound\\background_3.wav"};
    public static Clip clip;
    public static Clip quiz_clip=null;
    public static int bck_m_counter=0;
    public static int start_flag;
    public static int chosen=0;
    public static int highscheck;
    public static int hs_counter=0;
    public static int highscore[]=new int[10000000];
    public static int show_highscore=0;
    public static int rand1,rand2,rand3;
    public static float score=0,wrong_attempts=0;
    public static float percent_score=0;
    public static JFrame jf=new JFrame();
    public static JButton audio_toggle=new JButton();
    public static JPanel pane_img=new JPanel();
    public static int audio_counter=0;
    public static Object choice;
    public static Object[] menu_list = { ">>>choice<<<","1.Elements","2.Learn","3.Quiz","4.Exit" };
    public static Object[] stars = {"Enter Star(s)","1 star","2 stars","3 stars","4 stars","5 stars"}; 
    public static String[] options1={"Continue","Exit"};
    public static String arr_elements[]={"-Nil-","Hydrogen","Helium","Lithium","Beryllium","Boron","Carbon","Nitrogen","Oxygen"
                                        ,"Fluorine","Neon","Sodium","Magnesium","Aluminium","Sillicon","Phosphorus"
                                        ,"Sulphur","Chlorine","Argon","Potassium","Calcium","Scandium","Titanium","Vanadium"
                                        ,"Chromium","Manganese","Iron","Cobalt","Nickel","Copper","Zinc","Gallium","Germanium"
                                        ,"Arsenic","Selenium","Bromine","Krypton","Rubidium","Strontium","Yttrium","Zirconium"
                                        ,"Niobium","Molybdenum","Technetium","Ruthenium","Rhodium","Palladium","Silver"
                                        ,"Cadmium","Indium","Tin","Antimony","Tellurium","Iodine","Xenon","Cesium","Barium"
                                        ,"Lanthanum","Cerium","Praseodymium","Neodymium","Promethium","Samarium","Europium"
                                        ,"Gadolinium","Terbium","Dysprosium","Holmium","Erbium","Thulium","Ytterbium"
                                        ,"Lutetium","Hafnium","Tantalum","Tungsten","Rhenium","Osmium","Iridium","Platinum"
                                        ,"Gold","Mercury","Thallium","Lead","Bismuth","Polonium","Astatine","Radon","Francium"
                                        ,"Radium","Actinium","Thorium","Protactinium","Uranium","Neptunium","Plutonium","Americium"
                                        ,"Curium","Berkelium","Californium","Einstenium","Fermium","Mendelevium","Nobelium","Lawrencium"
                                        ,"Rutherfordium","Dubnium","Seaborgium","Neilsbohrium","Hassium","Meitnerium","Damstacium/Doust"
                                        ,"Roentgentium","Copernicium","Ununtrium","Ununquadium","Ununpentium","Ununhexium"
                                        ,"Ununseptium","Ununoctium"}; 
   public static String arr_symbols[]={"-Nil-","H","He","Li","Be","B","C","N","O","F","Ne","Na","Mg","Al","Si","P","S","Cl","Ar",
                                       "K","Ca","Sc","Ti","V","Cr","Mn","Fe","Co","Ni","Cu","Zn","Ga","Ge","As","Se","Br","Kr",
                                       "Rb","Sr","Y","Zr","Nb","Mo","Tc","Ru","Rh","Pd","Ag","Cd","In","Sn","Sb","Te","I","Xe",
                                       "Cs","Ba","La","Ce","Pr","Nd","Pm","Sm","Eu","Gd","Tb","Dy","Ho","Er","Tm","Yb","Lu","Hf",
                                       "Ta","W","Re","Os","Ir","Pt","Au","Hg","Tl","Pb","Bi","Po","At","Rn","Fr","Ra","Ac","Th",
                                       "Pa","U","Np","Pu","Am","Cm","Bk","Cf","Es","Fm","Md","No","Lr","Rf","Db","Sg","Bh","Hs","Mt",
                                       "Ds","Rg","Cn","Uut","Uuq","Uup","Uuh","Uus","UuO"};
   public static String arr_at_num[]={"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25",
                                      "26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49",
                                      "50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73",
                                      "74","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97",
                                      "98","99","100","101","102","103","104","105","106","107","108","109","110","111","112","113","114","115","116","117","118"}; 
                                      
   public static String arr_at_mass[]={"-Nil-","1.008","4.003","6.941","9.012","10.81",
                                       "12.01","14.01","16.00",
                                       "19.00","20.18","22.99","24.31","26.98",
                                       "28.09","30.97","32.06","35.45",
                                       "39.95","39.10","40.08","44.96","47.90",
                                       "50.94","52.00","54.94","55.85",
                                       "58.93","58.70","63.55","65.38","69.72",
                                       "72.59","74.92","78.96","79.90",
                                       "83.80","85.47","87.62","88.91","91.22",
                                       "92.91","95.94","97.00","101.10",
                                       "102.90","106.4","107.90","112.40","114.80","118.70",
                                       "121.80","127.60","126.90",
                                       "131.30","132.90","137.30","138.90","140.10",
                                       "140.90","144.20","145.00",
                                       "150.40","152.00","157.30","158.90","162.50",
                                       "164.90","167.30","168.90",
                                       "173.00","175.00","178.50","180.90","183.90",
                                       "186.20","190.20","192.20",
                                       "195.10","197.00","200.60","204.40","207.20",
                                       "209.00","209.00","210.00",
                                       "222.00","223.00","226.00","227.00","232.00",
                                       "231.00","238.00","237.00",
                                       "244.00","243.00","247.00","247.00","251.00","254.00",
                                       "257.00","258.00","259.00",
                                       "260.00","Not-Confirmed","Not-Confirmed","Not-Confirmed","Not-Confirmed"
                                       ,"Not-Confirmed","Not-Confirmed","Not-Confirmed","Not-Confirmed"
                                       ,"Not-Confirmed","Not-Confirmed","Not-Confirmed","Not-Confirmed"
                                       ,"Not-Confirmed","Not-Confirmed","Not-Confirmed"};                                
    public static String user_input_quiz="";
    public static Random rd=new Random();
    public void menu()
    {
      Object[] menu_list = { ">>>Choice<<<","1.Elements","2.Learn","3.Quiz","4.Exit" };
       
    }
    public void Learn()
    {
        JOptionPane.showMessageDialog(null,"::::::::::::::::::::::::::::::::::::::::::::\n1.Element - "+arr_elements[chosen]+"\n"
                                     +"2.Symbol - "+arr_symbols[chosen]+"\n"+"3.Atomic no. - "+arr_at_num[chosen]+"\n"
                                     +"4.Atomic mass - "+arr_at_mass[chosen]+" a.m.u",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
    }
    public void Name_Symbol()
    {
          int flag=1;
          highscheck=0;
          JOptionPane.showMessageDialog(null,"Enter Elements' Symbol(To Exit, type \"Exit\" whenever you want)",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);          
          while(flag==1)
                {
                    chosen=(int)(Math.random()*119)+1;
                    user_input_quiz=JOptionPane.showInputDialog(null,"Enter Symbol For::: "+arr_elements[chosen]);
                    while(user_input_quiz==null)
                    {
                        JOptionPane.showMessageDialog(null,"Type \"Exit\" whenever you want to EXIT",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        chosen=(int)(Math.random()*119)+1;
                        user_input_quiz=JOptionPane.showInputDialog("Enter Symbol For::: "+arr_elements[chosen]);
                    }
                    if(user_input_quiz==null)
                    {
                        break;
                    }
                    if(user_input_quiz.equalsIgnoreCase("Exit"))
                    {
                        highscore[highscheck]=hs_counter;
                        highscheck++;
                        hs_counter=0;
                        JOptionPane.showMessageDialog(null, "Thank You For Playing!!!!!",">>>GUI By ToZi<<<",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        
                        int total_attempts=(int)score+(int)wrong_attempts;
                        percent_score=(score/total_attempts)*100;
                        JOptionPane.showMessageDialog(null,"Score(s)::::"+(int)score+"\nPercent Score:::"+(float)percent_score+" %"+"\nWrong Attempts::::"+(int)wrong_attempts,
                                                     ">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        if(percent_score<=25.0)
                        {
                            JOptionPane.showMessageDialog(null,"You Need Improvement\nCheck out our Learn Section....",">>>GUI By ToZi<<<",
                                                         JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(percent_score>25.0 && percent_score<=50.0)
                        {
                           JOptionPane.showMessageDialog(null,"Remarks:::::Good",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(percent_score>50.0 && percent_score<=75.0)
                        {
                            JOptionPane.showMessageDialog(null,"Remarks:::::Very Good",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(percent_score>75.0 && percent_score<=90.0)
                        {
                            JOptionPane.showMessageDialog(null,"Remarks:::::Excellent",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Remarks:::::Outstanding",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                        flag=0;
                        continue;
                    }
                    else if(arr_symbols[chosen].equals(user_input_quiz))
                    {
                        clip.stop();
                          try
                       {
                            AudioInputStream quiz_music=AudioSystem.getAudioInputStream(new File("sound\\applause.wav"));
                            quiz_clip=AudioSystem.getClip();
                            quiz_clip.open(quiz_music);
                            quiz_clip.start();
                            
                          }
                         catch(Exception e)
                         {
                         //Sab milke nacho-nacho
                      }
                      
                       JOptionPane.showMessageDialog(null, "              Correct::\u2713",">>>GUI By ToZi<<<",
                                                     JOptionPane.INFORMATION_MESSAGE);
                      
                                                     
                       score++;
                       ++hs_counter;
                     if(audio_counter%2==0)
                       {
                      clip.start();
                      clip.loop(Clip.LOOP_CONTINUOUSLY);
                     }
                    }
                    else 
                    {
                        clip.stop();
                           try
                       {
                            AudioInputStream quiz_music=AudioSystem.getAudioInputStream(new File("sound\\boo.wav"));
                            quiz_clip=AudioSystem.getClip();
                            quiz_clip.open(quiz_music);
                            quiz_clip.start();
                            
                          }
                         catch(Exception e)
                         {
                         //Sab milke nacho-nacho
                      }
                        JOptionPane.showMessageDialog(null, "           Incorrect::\u2717\n          Answer ::: "+arr_symbols[chosen],">>>GUI By ToZi<<<",
                                                           JOptionPane.ERROR_MESSAGE);
                        wrong_attempts++;
                        if(score>0)
                        {
                        highscore[highscheck]=hs_counter;
                        highscheck++;
                        hs_counter=0;
                       }
                        if(audio_counter%2==0)
                       {
                      clip.start();
                      clip.loop(Clip.LOOP_CONTINUOUSLY);
                     }
                    }
                }
                
            }
    public void Symbol_Name()
         {
                int flag=1;
                JOptionPane.showMessageDialog(null,"Enter Elements' Name(To Exit, type \"Exit\" whenever you want)",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                
                while(flag==1)
                {
                    chosen=(int)(Math.random()*119)+1;
                    user_input_quiz=JOptionPane.showInputDialog("Enter Name For::: "+arr_symbols[chosen]);
                    while(user_input_quiz==null)
                    {
                        JOptionPane.showMessageDialog(null,"Type \"Exit\" whenever you want to EXIT",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        chosen=(int)(Math.random()*119)+1;
                        user_input_quiz=JOptionPane.showInputDialog("Enter Name For::: "+arr_symbols[chosen]);
                    }
                    if(user_input_quiz.equalsIgnoreCase("Exit"))
                    {
                        highscore[highscheck]=hs_counter;
                        
                        highscheck++;
                        hs_counter=0;
                        JOptionPane.showMessageDialog(null, "Thank You For Playing!!!!!",">>>GUI By ToZi<<<",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        
                        int total_attempts=(int)score+(int)wrong_attempts;
                        percent_score=(score/total_attempts)*100;
                        JOptionPane.showMessageDialog(null,"Score(s)::::"+(int)score+"\n"+"Percent Score:::"+(float)percent_score+" %\n"+"Wrong Attempts::::"+(int)wrong_attempts,
                                                      ">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        
                        if(percent_score<=25.0)
                        {
                            JOptionPane.showMessageDialog(null,"You Need Improvement\nCheck out our Learn Section....",">>>GUI By ToZi<<<",
                                                         JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(percent_score>25.0 && percent_score<=50.0)
                        {
                           JOptionPane.showMessageDialog(null,"Remarks:::::Good",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(percent_score>50.0 && percent_score<=75.0)
                        {
                            JOptionPane.showMessageDialog(null,"Remarks:::::Very Good",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(percent_score>75.0 && percent_score<=90.0)
                        {
                            JOptionPane.showMessageDialog(null,"Remarks:::::Excellent",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Remarks:::::Outstanding",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                        flag=0;
                        continue;
                    }
                    else if(arr_elements[chosen].equalsIgnoreCase(user_input_quiz))
                    {
                         clip.stop();
                          try
                       {
                            AudioInputStream quiz_music=AudioSystem.getAudioInputStream(new File("sound\\applause.wav"));
                            quiz_clip=AudioSystem.getClip();
                            quiz_clip.open(quiz_music);
                            quiz_clip.start();
                            
                          }
                         catch(Exception e)
                         {
                         //Sab milke nacho-nacho
                      }
                       JOptionPane.showMessageDialog(null, "              Correct::\u2713",">>>GUI By ToZi<<<",
                                                     JOptionPane.INFORMATION_MESSAGE);
                       score++;
                       hs_counter++;
                       if(audio_counter%2==0)
                       {
                      clip.start();
                      clip.loop(Clip.LOOP_CONTINUOUSLY);
                     }
                    }
                    else
                    {
                        clip.stop();
                           try
                       {
                            AudioInputStream quiz_music=AudioSystem.getAudioInputStream(new File("sound\\boo.wav"));
                            quiz_clip=AudioSystem.getClip();
                            quiz_clip.open(quiz_music);
                            quiz_clip.start();
                            
                          }
                         catch(Exception e)
                         {
                         //Sab milke nacho-nacho
                      }
                        JOptionPane.showMessageDialog(null, "           Incorrect::\u2717\n          Answer ::: "+arr_elements[chosen],">>>GUI By ToZi<<<",
                                                           JOptionPane.ERROR_MESSAGE);
                        wrong_attempts++;
                        if(score>0)
                        {
                        highscore[highscheck]=hs_counter;
                        highscheck++;
                        hs_counter=0;
                       }
                        if(audio_counter%2==0)
                       {
                      clip.start();
                      clip.loop(Clip.LOOP_CONTINUOUSLY);
                     }
                    }
                }
        
       }
       public void Atomic_No()
         {
                int flag=1;
                JOptionPane.showMessageDialog(null,"Enter Elements' Atomic No.(To Exit, type \"Exit\" whenever you want)",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                
                while(flag==1)
                {
                    chosen=(int)(Math.random()*119)+1;
                    user_input_quiz=JOptionPane.showInputDialog("Enter Atomic Number For::: "+arr_elements[chosen]);
                    while(user_input_quiz==null)
                    {
                        JOptionPane.showMessageDialog(null,"Type \"Exit\" whenever you want to EXIT",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        chosen=(int)(Math.random()*119)+1;
                        user_input_quiz=JOptionPane.showInputDialog("Enter Atomic Number For::: "+arr_elements[chosen]);
                    }
                    
                    if(user_input_quiz.equalsIgnoreCase("Exit"))
                    {
                        highscore[highscheck]=hs_counter;
                        
                        highscheck++;
                        hs_counter=0;
                        JOptionPane.showMessageDialog(null, "Thank You For Playing!!!!!",">>>GUI By ToZi<<<",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        
                        int total_attempts=(int)score+(int)wrong_attempts;
                        percent_score=(score/total_attempts)*100;
                        JOptionPane.showMessageDialog(null,"Score(s)::::"+(int)score+"\n"+"Percent Score:::"+(float)percent_score+" %\n"+"Wrong Attempts::::"+(int)wrong_attempts,
                                                      ">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        
                        if(percent_score<=25.0)
                        {
                            JOptionPane.showMessageDialog(null,"You Need Improvement\nCheck out our Learn Section....",">>>GUI By ToZi<<<",
                                                         JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(percent_score>25.0 && percent_score<=50.0)
                        {
                           JOptionPane.showMessageDialog(null,"Remarks:::::Good",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(percent_score>50.0 && percent_score<=75.0)
                        {
                            JOptionPane.showMessageDialog(null,"Remarks:::::Very Good",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(percent_score>75.0 && percent_score<=90.0)
                        {
                            JOptionPane.showMessageDialog(null,"Remarks:::::Excellent",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Remarks:::::Outstanding",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                        flag=0;
                        continue;
                    }
                    else if(arr_at_num[chosen].equalsIgnoreCase(user_input_quiz))
                    {
                        clip.stop();
                          try
                       {
                            AudioInputStream quiz_music=AudioSystem.getAudioInputStream(new File("sound\\applause.wav"));
                            quiz_clip=AudioSystem.getClip();
                            quiz_clip.open(quiz_music);
                            quiz_clip.start();
                            
                          }
                         catch(Exception e)
                         {
                         //Sab milke nacho-nacho
                      }
                       JOptionPane.showMessageDialog(null, "              Correct::\u2713",">>>GUI By ToZi<<<",
                                                     JOptionPane.INFORMATION_MESSAGE);
                       score++;
                       hs_counter++;
                       if(audio_counter%2==0)
                       {
                      clip.start();
                      clip.loop(Clip.LOOP_CONTINUOUSLY);
                     }
                    }
                    else
                    {
                        clip.stop();
                           try
                       {
                            AudioInputStream quiz_music=AudioSystem.getAudioInputStream(new File("sound\\boo.wav"));
                            quiz_clip=AudioSystem.getClip();
                            quiz_clip.open(quiz_music);
                            quiz_clip.start();
                            
                          }
                         catch(Exception e)
                         {
                         //Sab milke nacho-nacho
                      }
                        JOptionPane.showMessageDialog(null, "           Incorrect::\u2717\n          Answer ::: "+arr_at_num[chosen],">>>GUI By ToZi<<<",
                                                           JOptionPane.ERROR_MESSAGE);
                        wrong_attempts++;
                        if(score>0)
                        {
                        highscore[highscheck]=hs_counter;
                        highscheck++;
                        hs_counter=0;
                       }
                       if(audio_counter%2==0)
                       {
                      clip.start();
                      clip.loop(Clip.LOOP_CONTINUOUSLY);
                     }
                    }
                }
        
       }
       public void Atomic_Mass()
         {
                int flag=1;
                JOptionPane.showMessageDialog(null,"Enter Elements' Atomic Mass(To Exit, type \"Exit\" whenever you want)",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                
                while(flag==1)
                {
                    chosen=(int)(Math.random()*104)+1;
                    user_input_quiz=JOptionPane.showInputDialog("Enter Atomic Mass For::: "+arr_elements[chosen]);
                    while(user_input_quiz==null)
                    {
                        JOptionPane.showMessageDialog(null,"Type \"Exit\" whenever you want to EXIT",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        chosen=(int)(Math.random()*104)+1;
                        user_input_quiz=JOptionPane.showInputDialog("Enter Atomic Mass For::: "+arr_elements[chosen]);
                    }
                    
                    if(user_input_quiz.equalsIgnoreCase("Exit"))
                    {
                        highscore[highscheck]=hs_counter;
                        
                        highscheck++;
                        hs_counter=0;
                        JOptionPane.showMessageDialog(null, "Thank You For Playing!!!!!",">>>GUI By ToZi<<<",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        
                        int total_attempts=(int)score+(int)wrong_attempts;
                        percent_score=(score/total_attempts)*100;
                        JOptionPane.showMessageDialog(null,"Score(s)::::"+(int)score+"\n"+"Percent Score:::"+(float)percent_score+" %\n"+"Wrong Attempts::::"+(int)wrong_attempts,
                                                      ">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        
                        if(percent_score<=25.0)
                        {
                            JOptionPane.showMessageDialog(null,"You Need Improvement\nCheck out our Learn Section....",">>>GUI By ToZi<<<",
                                                         JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(percent_score>25.0 && percent_score<=50.0)
                        {
                           JOptionPane.showMessageDialog(null,"Remarks:::::Good",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(percent_score>50.0 && percent_score<=75.0)
                        {
                            JOptionPane.showMessageDialog(null,"Remarks:::::Very Good",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(percent_score>75.0 && percent_score<=90.0)
                        {
                            JOptionPane.showMessageDialog(null,"Remarks:::::Excellent",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Remarks:::::Outstanding",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                        flag=0;
                        continue;
                    }
                    else if(arr_at_mass[chosen].equalsIgnoreCase(user_input_quiz))
                    {
                        clip.stop();
                          try
                       {
                            AudioInputStream quiz_music=AudioSystem.getAudioInputStream(new File("sound\\applause.wav"));
                            quiz_clip=AudioSystem.getClip();
                            quiz_clip.open(quiz_music);
                            quiz_clip.start();
                            
                          }
                         catch(Exception e)
                         {
                         //Sab milke nacho-nacho
                      }
                       JOptionPane.showMessageDialog(null, "              Correct::\u2713",">>>GUI By ToZi<<<",
                                                     JOptionPane.INFORMATION_MESSAGE);
                       score++;
                       hs_counter++;
                       if(audio_counter%2==0)
                       {
                      clip.start();
                      clip.loop(Clip.LOOP_CONTINUOUSLY);
                     }
                    }
                    else
                    {
                        clip.stop();
                           try
                       {
                            AudioInputStream quiz_music=AudioSystem.getAudioInputStream(new File("sound\\boo.wav"));
                            quiz_clip=AudioSystem.getClip();
                            quiz_clip.open(quiz_music);
                            quiz_clip.start();
                            
                          }
                         catch(Exception e)
                         {
                         //Sab milke nacho-nacho
                      }
                        JOptionPane.showMessageDialog(null, "           Incorrect::\u2717\n          Answer ::: "+arr_at_mass[chosen]+" a.m.u",">>>GUI By ToZi<<<",
                                                           JOptionPane.ERROR_MESSAGE);
                        wrong_attempts++;
                        if(score>0)
                        {
                        highscore[highscheck]=hs_counter;
                        highscheck++;
                        hs_counter=0;
                       }
                       if(audio_counter%2==0)
                       {
                      clip.start();
                      clip.loop(Clip.LOOP_CONTINUOUSLY);
                     }
                    }
                }
        
       }
    public void highscore()
    {
        show_highscore=highscore[highscheck];
        for(int check=0;check<highscore.length-1;check++)
        {
          if(highscore[check]>show_highscore)
          {
             show_highscore=highscore[check];
          }
        }
        
     }
     public void GUI_background()
     {
         try
       {
        AudioInputStream back_music=AudioSystem.getAudioInputStream(new File(background_m[0]));
        clip=AudioSystem.getClip();
        clip.open(back_music);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
       }
       catch(Exception e)
        {
            //
        }
        Border basicb= BorderFactory.createLineBorder(Color.black);
        TitledBorder title=BorderFactory.createTitledBorder(basicb,"Developed By Shashank Shirol a.k.a ToZi");
        this.setUndecorated(true);
        this.setTitle("Creator::TOZI");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JButton info_btn=new JButton();
        info_btn.setText("Info.");
        info_btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
                info(event);
            }
        });
        JButton change_background=new JButton();
        change_background.setText("Change Background"); 
        change_background.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
                background(event);
            }
        });
        JButton start=new JButton();
        start.setText("Start!!!");
        start.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event)
            {
                start_option(event);
            }
           });
           JButton exit=new JButton();
           exit.setText("Exit");
           exit.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent event)
               {
                   exit(event);
                }
            });
        
        audio_toggle.setIcon(new ImageIcon("Icons\\sn1.gif"));
        audio_toggle.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
                audio_t(event);
            }
        });
        JButton bck_m=new JButton();
        bck_m.setText("Change Background Music");
        bck_m.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
                backgroundmusic(event);
            }
        });
        panel_background.add(start);
        panel_background.add(change_background);
        panel_background.add(bck_m);
        panel_background.add(info_btn);
        panel_background.add(audio_toggle);
        panel_background.add(exit);
        panel_background.setBackground(new Color(rand1,rand2,rand3));
        title.setTitleJustification(TitledBorder.CENTER);
        panel_background.setBorder(title);
        this.add(panel_background);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        }
        public void info(ActionEvent event)
        {
            JOptionPane.showMessageDialog(null,copyright,">>>GUI BY ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
            jf.setVisible(false);
            jf.setVisible(true);
        }
        public void background(ActionEvent event)
        {
            rand1=rd.nextInt(256);
            rand2=rd.nextInt(256);
            rand3=rd.nextInt(256);
            panel_background.setBackground(new Color(rand1,rand2,rand3));
            pane_img.setBackground(new Color(rand1,rand2,rand3));
            jf.setVisible(false);
            jf.setVisible(true);
        }
        public void exit(ActionEvent event)
        {
            clip.stop();
            JOptionPane.showMessageDialog(null,"Contact Me at:::\nShashank.shirol1@gmail.com",">>>GUI BY ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(false);
            jf.setVisible(false);
            System.exit(0);
        }
        public void audio_t(ActionEvent event)
        {
           audio_counter++;
           if(audio_counter%2!=0)
          {
           clip.stop();
           audio_toggle.setIcon(new ImageIcon("Icons\\sn_mute.png"));
          }
          else
          {
              clip.start();
              clip.loop(Clip.LOOP_CONTINUOUSLY);
              audio_toggle.setIcon(new ImageIcon("Icons\\sn1.gif"));
            }
           jf.setVisible(false);
           jf.setVisible(true);
        }
        public void backgroundmusic(ActionEvent event)
        {
          jf.setVisible(false);
          jf.setVisible(true);
          clip.stop();
          bck_m_counter++;
          if(bck_m_counter>2)
          {
                bck_m_counter=0;
          }
             try
          {
           AudioInputStream back_music=AudioSystem.getAudioInputStream(new File(background_m[bck_m_counter]));
           clip=AudioSystem.getClip();
           clip.open(back_music);
           clip.start();
           clip.loop(Clip.LOOP_CONTINUOUSLY);
          }
          catch(Exception e)
          {
            //Sab milke nacho-nacho
          }
          
        }
        public void start_option(ActionEvent event)
        {
            
            String[] opt_start={"Yes","No"};
            start_flag=JOptionPane.showOptionDialog(null,"You Won't Be Able To Change Background Colour Or The Music Until The End,\nAre Do You Want To Start?",
                                                     ">>>GUI BY ToZi<<<",JOptionPane.WARNING_MESSAGE,1,null,opt_start,null);
            if(start_flag==0)
        {
        JOptionPane.showMessageDialog(null,"Please Ignore any Errors as this is still in BETA stage!!",">>>GUI BY ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
        String[] options1={"Continue!!","Exit"};
        int selected_options1=JOptionPane.showOptionDialog(null,"        Welcome to ChemX.™\n A fun way to learn Chemistry\n",">>>GUI By ToZi<<<",JOptionPane.WARNING_MESSAGE,1,null,
                                                                  options1,options1[0]);
        while(selected_options1==-1)
           {
                    
                    selected_options1=JOptionPane.showOptionDialog(null,"Enter Your Choice!!!",">>>GUI By ToZi<<<",JOptionPane.WARNING_MESSAGE,1,null,
                                                                  options1,options1[0]);
           }
            
           if(selected_options1==0)
           {
            
            JPanel panel=new JPanel();
            JTextField textf=new JTextField(7);
             panel.add(new  JLabel("Enter Any Element's Atomic Number::"));
            panel.add(textf);
        
        
        menu();
        choice = JOptionPane.showInputDialog(null,"Choose Your Option:::", "Creator::ToZi",JOptionPane.INFORMATION_MESSAGE, null,
                                                          menu_list, menu_list[0]);
        while(choice==menu_list[0])
        {
            choice = JOptionPane.showInputDialog(null,"Choose Your Option:::", "Creator::ToZi",JOptionPane.INFORMATION_MESSAGE, null,
                                                          menu_list, menu_list[0]);
        }
                                                          
        if(choice==null)
        {
            JOptionPane.showMessageDialog(null,"Thank You For using the Program",">>>GUI BY TOZI<<<",JOptionPane.INFORMATION_MESSAGE);
            
        }
        while (choice!=menu_list[4] && choice!=null)
        {
            if(choice==menu_list[1])
            {
                            
              JOptionPane.showMessageDialog(null,"Under Construction",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
              menu();
              choice = JOptionPane.showInputDialog(null,"Choose Your Option:::", ">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE, null,
                                                          menu_list, menu_list[0]);
               while(choice==menu_list[0])
           {
            choice = JOptionPane.showInputDialog(null,"Choose Your Option:::", "Creator::ToZi",JOptionPane.INFORMATION_MESSAGE, null,
                                                          menu_list, menu_list[0]);
           }                                           
            }
          else  if(choice==menu_list[2])
            {
                int learn_flag=0;
                
                
                
                String[] options={"Yes","No"};
                while(learn_flag==0)
                {
                    chosen=JOptionPane.showOptionDialog(null, panel, ">>>GUI By ToZi<<<",
                             JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                             null, options1, textf.getText());
                    if(chosen==1 || chosen==-1)
                    {
                           learn_flag=1;
                           continue;
                    }
                    while((textf.getText()).equalsIgnoreCase(""))
                    {
                        chosen=JOptionPane.showOptionDialog(null, panel, ">>>GUI By ToZi<<<",
                             JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                             null, options1, textf.getText());
                        if(chosen==1 || chosen==-1)
                       {
                           learn_flag=1;
                           continue;
                       }
                    }
                    chosen=Integer.parseInt(textf.getText());
                    while((chosen<=0 || chosen>118) &&learn_flag!=1)
                    {
                      
                      JOptionPane.showMessageDialog(null,"Enter Appropriate Atomic Number",">>>GUI By ToZi<<<",JOptionPane.ERROR_MESSAGE);
                      chosen=JOptionPane.showOptionDialog(null, panel, "Enter a Number",
                             JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                             null, options1, options1[0]);
                             
                      if(chosen==1 || chosen==-1)
                      {
                           learn_flag=1;
                           break;
                      }
                      chosen=Integer.parseInt(textf.getText());
                    }
                    if(learn_flag!=1)
                    {
                                       
                     Learn();
                    
                     learn_flag=JOptionPane.showOptionDialog(null,"Do you want to continue?",">>>GUI By ToZi<<<",JOptionPane.WARNING_MESSAGE,3,null,
                                                          options,options[0]);
                    while(choice==menu_list[0])
                     {
                        
                     choice = JOptionPane.showInputDialog(null,"Choose Your Option:::", "Creator::ToZi",JOptionPane.INFORMATION_MESSAGE, null,
                                                          menu_list, menu_list[0]);
                     }                                      
                    }                                      
                } 
                menu();
                choice = JOptionPane.showInputDialog(null,"Choose Your Option:::", ">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE, null,
                                                          menu_list, menu_list[0]);
                while(choice==menu_list[0])
                {
                                       
                    choice = JOptionPane.showInputDialog(null,"Choose Your Option:::", "Creator::ToZi",JOptionPane.INFORMATION_MESSAGE, null,
                                                          menu_list, menu_list[0]);
                }                                          
                continue;                                          
            }
            else if(choice==menu_list[3])
            {
                clip.stop();
                      try
                       {
                            AudioInputStream quiz_music=AudioSystem.getAudioInputStream(new File("sound\\quiz_start.wav"));
                            quiz_clip=AudioSystem.getClip();
                            quiz_clip.open(quiz_music);
                            quiz_clip.start();
                            
                          }
                         catch(Exception e)
                         {
                         //Sab milke nacho-nacho
                      }  
                 if(audio_counter%2==0)
                       {
                      clip.start();
                      clip.loop(Clip.LOOP_CONTINUOUSLY);
                     }     
                Object[] games={">>>Choose One<<<","Atomic Masses","Atomic Number","Name to Symbol","Symbol to Name"};
                Object game_choice=JOptionPane.showInputDialog(null,"Enter Your Option",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE,null,
                                                                 games,games[0]);
                while(game_choice==games[0])
                {
                    
                    game_choice=JOptionPane.showInputDialog(null,"Enter Your Option",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE,null,
                                                                 games,games[0]);
                }
                 if (game_choice==null)
                 {
                     menu();
                     choice = JOptionPane.showInputDialog(null,"Choose Your Option:::", ">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE, null,
                                                          menu_list, menu_list[0]);
                        while(choice==menu_list[0])
                      {
                                                   
                          choice = JOptionPane.showInputDialog(null,"Choose Your Option:::", "Creator::ToZi",JOptionPane.INFORMATION_MESSAGE, null,
                                                          menu_list, menu_list[0]);
                     }                                     
                     
                 }
                 else if(game_choice==games[1])
                 {
                                         
                     Atomic_Mass();
                     highscore();
                     JOptionPane.showMessageDialog(null,"Longest Streak of correct answers:::: "+show_highscore,">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                    }
                 else if(game_choice==games[2])
                 {
                     
                     Atomic_No();
                     highscore();
                     JOptionPane.showMessageDialog(null,"Longest Streak of correct answers:::: "+show_highscore,">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                 }
                else if(game_choice==games[3])
                {
                  
                  Name_Symbol();
                  highscore();
                  JOptionPane.showMessageDialog(null,"Longest Streak of correct answers:::: "+show_highscore,">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(game_choice==games[4])
                {
                    
                    Symbol_Name();
                    highscore();
                    JOptionPane.showMessageDialog(null,"Longest Streak of correct answers:::: "+show_highscore,">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                }
                
        }
    }
                
        String[] options={"Yes!!!","I will do it later!!"};
        int Rating=JOptionPane.showOptionDialog(null,"Would you like to Rate the Program?",">>>GUI By ToZi<<<",JOptionPane.WARNING_MESSAGE,3,
                                                null,options,options[0]);
                                             
        if(Rating==0)
        {
                
            Object selected_stars=JOptionPane.showInputDialog(null,"",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE,null,
                                                               stars,stars[0]);
            if(selected_stars!=null)
            {
                
                
             JOptionPane.showMessageDialog(null,"Thank You!!! for rating the program with "+selected_stars,">>>GUI By ToZi<<<",JOptionPane.QUESTION_MESSAGE); 
            }
            if(selected_stars==stars[5])
            {
                  clip.stop();
                      try
                       {
                            AudioInputStream quiz_music=AudioSystem.getAudioInputStream(new File("sound\\goo.wav"));
                            quiz_clip=AudioSystem.getClip();
                            quiz_clip.open(quiz_music);
                            quiz_clip.start();
                            
                          }
                         catch(Exception e)
                         {
                         //Sab milke nacho-nacho
                      }
                JOptionPane.showMessageDialog(null,"You're Awesome!!!!!",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
                if(audio_counter%2==0)
                       {
                      clip.start();
                      clip.loop(Clip.LOOP_CONTINUOUSLY);
                     }
            }
        
        }
                
        JOptionPane.showMessageDialog(null,"Programmed By:ToZi",">>>GUI By ToZi<<<",JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "        Beyond Truth!!!!!\n                               -Shashank","Made By ToZi..",
                                      JOptionPane.INFORMATION_MESSAGE);
        
                                  
         
        
   }
}                      
            jf.setVisible(false);
            jf.setVisible(true); 
        }
        
    public static void main(String args[]) throws Exception
    {
        ChemX obj=new ChemX();
        obj.GUI_background();
        rand1=rd.nextInt(256);
        rand2=rd.nextInt(256);
        rand3=rd.nextInt(256);
        panel_background.setBackground(new Color(rand1,rand2,rand3));
        jf.setSize(700,400);
        jf.setUndecorated(true);
        
        pane_img.setBackground(new Color(rand1,rand2,rand3));
        Icon icon=new ImageIcon("Icons\\welcome3.gif");
        JLabel lbl_img=new JLabel();
        lbl_img.setIcon(icon);
        pane_img.add(lbl_img);
        jf.add(pane_img);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        
        
}
}