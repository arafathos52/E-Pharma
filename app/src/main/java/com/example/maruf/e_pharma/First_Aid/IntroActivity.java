package com.example.maruf.e_pharma.First_Aid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shuvo.medicare.R;

public class IntroActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    public static int adapter;
    public String adap;


    private Button btnSkip, btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        adap= extras.getString("adapter");
        adapter = Integer.parseInt(adap);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_intro);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);

        layouts = new int[]{
                R.layout.slide_1,
                R.layout.slide_2,
                R.layout.slide_3};


        // adding bottom dots
        addBottomDots(0);

        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);


    }



    public void btnSkipClick(View v) {
        int current = getItem(1);
        if (current==1) {
            launchHomeScreen();
        } else {
            current--;
            viewPager.setCurrentItem(current-1);
        }

    }

    public void btnNextClick(View v) {

        int current = getItem(1);
        if (current < layouts.length) {
            viewPager.setCurrentItem(current);
        } else {
            launchHomeScreen();
        }
    }


    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                //btnNext.setText(getString(R.string.start));
                //btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
               // btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };


    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.dot_inactive));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(getResources().getColor(R.color.dot_active));
    }


    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {

        /*Intent intent = new Intent(IntroActivity.this,MainActivity.class);
        intent.putExtra("fragmentNumber", 1);
        startActivity(intent);*/
        finish();


    }

    public class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        private LayoutInflater inflater;
        public String[] layout1;
        public String[] layout2;
        public String[]  layout3;
        public int[] images1;
        public int[] images2;
        public int[] images3;
        String s1=null;
        int count =0;

        public ViewPagerAdapter() {
            initializer();
        }



        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            s1="";
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(layouts[position], null); //log.xml is your file.
            TextView tv = (TextView)view.findViewById(R.id.textView11);
            //get a reference to the textview on the layout.xml file.
            ImageView iv = (ImageView)view.findViewById(R.id.imageView10);
            Log.d("adap",adapter+"");
            if(position==0){ s1 = layout1[adapter]; iv.setImageResource(images1[adapter]);}
            else if(position==1){ s1 = layout2[adapter]; iv.setImageResource(images2[adapter]);}
            else if(position==2){ s1 = layout3[adapter]; iv.setImageResource(images3[adapter]);}

            tv.setText(s1);







            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
        public void initializer()
        {

            layout1 = new String[]
                    {"Food poisoning is a food borne disease.Home remedies to soothe food poisoning symptoms may help speed recovery and may include:\n" +
                            "Rest," +
                            "Rehydration," +
                            "Slowly begin to eat bland foods like rice, bananas, toast, gelatin. " +
                            "Avoid alcohol, nicotine, fatty, and seasoned or spicy foods",
                            "Drink a rehydration drink, water, juice, or sports drink to replace fluids and minerals. Drink 2 qt (2 L) of cool liquids over the next 2 to 4 hours. You should drink at least 10 glasses of liquid a day to replace lost fluids. ",
                            "People with an ideal weight and who are physically fit and active generally have a blood pressure reading lower than what is considered normal at 120/80 mm Hg. Yet, this is not unhealthy, especially when the symptoms are not experienced.",
                            "A stroke is caused by a blood clot or a blood vessel leak in the brain. When the brain stops receiving a steady blood flow, it stops working. There are three main types of stroke: ischemic, hemorrhagic, and TIA (mini stroke)." ,
                            "Move the Person. Take the person out of the water.\n" +
                                    "* Check for Breathing\n" +
                                    "* If the Person is Not Breathing, Check Pulse\n" +
                                    "*If There is No Pulse, Start CPR.Carefully place person on back.",
                            "Stop Burning Immediately: Put out fire or stop the person's contact with hot liquid, steam, or other material.\n" +
                                    "Help the person stop, drop, and roll to smother flames.\n" +
                                    "Remove smoldering material from the person." +
                                    "Remove hot or burned clothing. If clothing sticks to skin, cut or tear around it.",
                            "The initial treatment may require a drip into a vein to give you fluid or even a blood transfusion if the bleeding is severe. This may not be necessary if the bleeding has been minor and has stopped. ",
                            "During a panic attack, the body goes into “fight or flight” mode which can cause distressing symptoms including: Sweating , Trembling, Palpitations , Shortness of breath , Chest pain , Dizziness , Hyperventilation , Sickness",
                            "Pursed-lip breathing :\n" + "This is a simple way to control shortness of breath. It helps quickly slow your pace of breathing, which makes each breath deeper and more effective. It also helps release air that’s trapped in your lungs.",
                            "Remove any obvious dirt or debris from the wound. Don't remove large or deeply embedded objects. Don't probe the wound or attempt to clean it yet. Your first job is to stop the bleeding.",
                            "Two types of stomach acid-reducing medicines:\n" +
                                    "1. H2 blockers, or histamine-2 blockers, which include cimetidine, rantidine and famotidine\n" +
                                    "2. Proton pump inhibitors (PPIs), which include omeprazole, lansoprazole and esomeprazole.",
                            "Caution:\n" +
                                    " Don't touch the injured person if he or she is still in contact with the electrical current. " +
                                    " Call  your local emergency number if the source of the burn is a high-voltage wire. " +
                                    "Don't move a person with an electrical injury unless he or she is in immediate danger."



                    };
            layout2 = new String[]
                    {"Food poisoning most commonly causes:\n" +
                            "abdominal cramps,\n" +
                            "vomiting, and\n" +
                            "diarrhea.",
                            "You can make an inexpensive rehydration drink at home. But do not give this homemade drink to children younger than 12. Small variations can make the drink less effective or even harmful. Mix the following:\n" +
                                    "* 1 quart water\n" +
                                    "* ½ teaspoon table salt\n" +
                                    "* 6 teaspoons sugar",
                            "Salt water helps treat low blood pressure because the sodium in salt increases blood pressure. Do not overdo this remedy though, as excess salt can prove to be unhealthy. Simply mix one-half teaspoon of salt in a glass of water and drink it. You can also drink sports beverages.",
                            "Natural remedies and prevention tips for stroke : Stop smoking,Reduce alcohol consumption, Reduce stress ,Be more physically active,Avoid junk food",
                            "For an adult or child, place the heel of one hand on the center of the chest at the nipple line. You can also push with one hand on top of the other. For an infant, place two fingers on the breastbone.",
                            "Cool Burn: " +
                                    "\n" +
                                    "Hold burned skin under cool (not cold) running water or immerse in cool water until pain subsides.\n" +
                                    "Use compresses if running water isn’t available.",
                            "However, an initial treatment to stop any ongoing bleeding can often be done by using instruments that can be passed down the endoscope . Occasionally, emergency surgery is needed to control severe ongoing bleeding.",
                            "First aid treatment for a panic attack\n" +
                                    "1. Remove any triggers of the panic attack (or remove the patient from the trigger!)\n" +
                                    "2. Provide lots of reassurance and remain calm yourself\n" +
                                    "3. Focus on controlling the patient’s breathing – encourage them to breath in slowly",
                            "Sitting forward supported by a table :\n"+"1. Sit in a chair with your feet flat on the floor\n" +
                                    "2. Lean your chest slightly forward and rest your arms on the table.\n" +
                                    "3. Rest your head on your forearms or on a pillow.",
                            "Stop the bleeding. Place a sterile bandage or clean cloth on the wound. Press the bandage firmly with your palm to control bleeding. Maintain pressure by binding the wound .",
                            "If your ulcer is related to an H. pylori infection, you may also receive short-term triple therapy, consisting of one acid-reducing agent and two antibiotics. Generally, triple therapy successfully eradicates the bacteria in up to 90 per cent of cases.",
                            "Take these actions immediately :\n" +
                                    "Turn off the source of electricity, if possible. If not, move the source away from you and the person, using a dry, nonconducting object made of cardboard, plastic or wood." +
                                    "Begin CPR if the person shows no signs of circulation, such as breathing, coughing or movement."


                    };

            layout3 = new String[]
                    {"Maintaining good hydration is the first priority when treating food poisoning. Hospitalization may be appropriate if the patient is dehydrated or if they have other underlying medical conditions that become unstable because of the fluid or electrolyte imbalance in their body.",
                            "Acetaminophen (for example, Tylenol) or ibuprofen (for example, Advil) may be used. This can be given by mouth if the affected person is not vomiting or as a rectal suppository if they cannot take anything by mouth.",
                            "These are helpfull :- Coffee,Raisins,Holy Basil,Beetroot Juice,Licorice Root",
                            "Emergency procedures include giving medication directly to the brain and performing a mechanical clot removal. Other procedures for ischemic stroke treatment include carotid endarterectomy, angioplasty, and stents.",
                            "If you've been trained in CPR, you can now open the airway by tilting the head back and lifting the chin.Pinch the nose of the victim closed. Take a normal breath, cover the victim's mouth with yours to create an airtight seal, and then give 2 one-second breaths for the chest to rise.",
                            "Treat Pain - Give over-the-counter pain reliever such as ibuprofen (Advil, Motrin), acetaminophen (Tylenol), or naproxen (Aleve).",
                            "Self Care for Vomiting\n" +
                                    "Have the person drink small amounts of water, sports drinks, or clear liquids.\n" +
                                    "Don't give the person solid food until vomiting has stopped.\n" +
                                    "Try small amounts of the BRAT diet: bananas, rice, applesauce, and toast.",
                            "Important: a panic attack can sometimes look very similar to an asthma attack, and the two can occur together. For this reason a paper bag is not recommended for re-breathing, if you have any concerns about the patient’s breathing then seek emergency medical help",
                            "Standing with supported back:\n"+"1.Stand near a wall, facing away, and rest your hips on the wall.\n" +
                                    "2. Keep your feet shoulder width apart and rest your hands on your thighs.",
                            "Special cases:\n" +
                                    "1.Don't put direct pressure on an eye injury or embedded object.\n" +
                                    "2.Don't reposition or put pressure on displaced organs. Cover the wound with a clean dressing.",
                            "Tips to prevent gastric pain: Eat smaller but more frequent meals, Eat on time and avoid skipping meals ,Quit smoking, Drink alcohol in moderation",
                            "Try to prevent the injured person from becoming chilled.\n" +
                                    "Apply a bandage. Cover any burned areas with a sterile gauze bandage, if available, or a clean cloth. Don't use a blanket or towel, because loose fibers can stick to the burns. "

                    };
            images1 = new int[]
                    {R.drawable.food1,
                            R.drawable.dehydration1,
                            R.drawable.pressure1,
                            R.drawable.stroke1,
                            R.drawable.drowning1,
                            R.drawable.burn1,
                            R.drawable.vomiting1,
                            R.drawable.panic1,
                            R.drawable.breath1,
                            R.drawable.bleeding1,
                            R.drawable.gastric1,
                            R.drawable.shock1

                    };
            images2 = new int[]
                    {R.drawable.food2,
                            R.drawable.dehydration2,
                            R.drawable.pressure2,
                            R.drawable.stroke2,
                            R.drawable.drowning2,
                            R.drawable.burn2,
                            R.drawable.vomiting2,
                            R.drawable.panic2,
                            R.drawable.breath2,
                            R.drawable.bleeding2,
                            R.drawable.gastric2,
                            R.drawable.shock2

                    };
            images3 = new int[]
                    {R.drawable.food3,
                            R.drawable.dehydration3,
                            R.drawable.pressure3,
                            R.drawable.stroke3,
                            R.drawable.drowning3,
                            R.drawable.burn3,
                            R.drawable.vomiting3,
                            R.drawable.panic3,
                            R.drawable.breath3,
                            R.drawable.bleeding3,
                            R.drawable.gastric3,
                            R.drawable.shock3


                    };

        }
    }


}
