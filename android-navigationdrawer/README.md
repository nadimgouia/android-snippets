android-navigationdrawer
========================

####Description

This repo simply contains a sample project and notes for using the Android Studio Release 1 __Navigation Drawer__ Activity template to add your own custom __Fragments__ to the __DrawerLayout's__ FrameLayout.

####Drawer Layout Customization Steps

1. Open a new Android Studio project.  When prompted, choose the __Navigation Drawer Activity
1. Add to app/res/values/strings.xml for new sections (see title_sectionx below)
 
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name">WA Invasives</string>
    <string name="title_section1">Priority Invasive Plants</string>
    <string name="title_section2">Priority Invasive Animals</string>
    <string name="title_section3">Report a Sighting</string>
    <string name="title_section4">Map</string>
    <string name="title_section5">About WISC</string>
    <string name="navigation_drawer_open">Open navigation drawer</string>
    <string name="navigation_drawer_close">Close navigation drawer</string>
    <string name="action_example">Example action</string>
    <string name="action_settings">Settings</string>
</resources>
 
2. Update onSectionAttached() method in app/java/[package]/MainActivity
 
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
            case 4: //added case
                mTitle = getString(R.string.title_section4); 
                break;
            case 5: //added case
                mTitle = getString(R.string.title_section5);
                break;
        }
    }
 
3. For each menu item, when tapped, you want to replace with a new Fragment class.  Thus, do the following...
 
a. create a new Fragment class for each drawer item - with the following template below (e.g. invasive plant fragment)
 
public class PlantFragment extends Fragment {
 
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
 
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlantFragment newInstance(int sectionNumber) {
        PlantFragment fragment = new PlantFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
 
    public PlantFragment() {
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plant, container, false);
        return rootView;
    }
 
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
 
b. In app/java/<package>/MainActivity.java, update the onNavigationDrawerItemSelected() callback method ...  for example ...
 
    @Override
    public void onNavigationDrawerItemSelected(int position) {
 
        /*
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
        */
 
        FragmentManager fragmentManager = getFragmentManager();
        Toast toast = null;
        switch (position) {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, PlantFragment.newInstance(position + 1))
                        .commit();
 
                //TEST
                toast = Toast.makeText(getApplicationContext(), "index " + position, Toast.LENGTH_SHORT);
                toast.show();
 
                break;
 
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, AnimalFragment.newInstance(position + 1))
                        .commit();
 
                //TEST
                toast = Toast.makeText(getApplicationContext(), "index " + position, Toast.LENGTH_SHORT);
                toast.show();
 
                break;
 
            default:
 
                break;
        }
    }
    
####Connect
* Twitter: [@clintcabanero](http://twitter.com/clintcabanero)
* GitHub: [ccabanero](http:///github.com/ccabanero)

####References

[Drawer Layout](https://developer.android.com/reference/android/support/v4/widget/DrawerLayout.html)