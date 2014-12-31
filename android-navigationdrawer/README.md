android-navigationdrawer
========================

####Description

This repo simply contains a sample project and notes for using the Android Studio Release 1 __Navigation Drawer__ Activity template to add your own custom __Fragments__ to the __DrawerLayout's__ FrameLayout.

####Drawer Layout Customization Steps

1) Open a new Android Studio project.  When prompted, choose the __Navigation Drawer__ Activity.

2) Add to the titles of your drawer list items to __app/res/values/strings.xml__ 

For example ...
 
````
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name">NavigationDrawer</string>
    <string name="title_section1">My First Section</string>
    <string name="title_section2">My Second Section</string>
    <string name="title_section3">My Third Section</string>
    <string name="title_section4">My Fourth Section</string>
    <string name="title_section5">My Fifth Section</string>
    <string name="navigation_drawer_open">Open navigation drawer</string>
    <string name="navigation_drawer_close">Close navigation drawer</string>
    <string name="action_example">Example action</string>
    <string name="action_settings">Settings</string>
    <string name="hello_blank_fragment">Hello blank fragment</string>
</resources>
````
 
3) Update your main activity's __onSectionAttached()__ method in app/java/[YourPackage]/[YourMainActivity] to switch between your new drawer list items titles.

For example ...

````
public void onSectionAttached(int number) {
       switch (number) {
           case 1:
               mTitle = getString(R.string.title_section1);
               break;
           case 2:
               mTitle = getString(R.string.title_section2);
               break;
           case 3:
               mTitle = getString(R.string.title_section3);
               break;
           case 4:
               mTitle = getString(R.string.title_section4);
               break;
           case 5:
               mTitle = getString(R.string.title_section5);
               break;
       }
   }
````
4) In app/java/[YourPackageName]/[YourMainActivity].java, when the DrawerListView's adapter is being set ... add references to the string array for each drawer item ...

````
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDrawerListView = (ListView) inflater.inflate(
                R.layout.fragment_navigation_drawer, container, false);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
        mDrawerListView.setAdapter(new ArrayAdapter<String>(
                getActionBar().getThemedContext(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                
                //UPDATE HERE....
                new String[]{
                        getString(R.string.title_section1),
                        getString(R.string.title_section2),
                        getString(R.string.title_section3),
                        getString(R.string.title_section4),
                        getString(R.string.title_section5)
                }));
        mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
        return mDrawerListView;
    }
````


5) When user taps on a drawer list item, we will replace the Fragment inside the FrameLayout with our custom Fragment classes using the following steps ...
 
* Create a new Fragment class for each drawer item using the code below as a general template ... 
 
````
public class Section1Fragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Section1Fragment newInstance(int sectionNumber) {
        Section1Fragment fragment = new Section1Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Section1Fragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_section1, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}

````
 * In app/java/[YourPackageName]/[YourMainActivity].java, remove the class declaration for the __PlaceholderFragment__.  We are no longer going to use the PlaceholderFragment class.
 
 * In app/java/[YourPackageName]/[YourMainActivity].java, update the __onNavigationDrawerItemSelected()__ callback method to replace the fragment based on the position of the drawer list item the user tapped ...
 
 ````
    @Override
    public void onNavigationDrawerItemSelected(int position) {

        /*
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
        */


        android.app.FragmentManager fragmentManager = getFragmentManager();
        Toast toast = null;
        switch (position) {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, Section1Fragment.newInstance(position + 1))
                        .commit();

                //TEST
                toast = Toast.makeText(getApplicationContext(), "index " + position, Toast.LENGTH_SHORT);
                toast.show();

                break;

            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, Section2Fragment.newInstance(position + 1))
                        .commit();

                //TEST
                toast = Toast.makeText(getApplicationContext(), "index " + position, Toast.LENGTH_SHORT);
                toast.show();

                break;

            default:

                break;
        }
    }
 ````
 
####Connect

* Twitter: [@clintcabanero](http://twitter.com/clintcabanero)
* GitHub: [ccabanero](http:///github.com/ccabanero)

####References

[Drawer Layout](https://developer.android.com/reference/android/support/v4/widget/DrawerLayout.html)