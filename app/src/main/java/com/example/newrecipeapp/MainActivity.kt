package com.example.newrecipeapp



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newrecipeapp.databinding.ActivityMainBinding
import com.example.newrecipeapp.fragments.HomeFragment
import com.example.newrecipeapp.fragments.AccountFragment
import com.example.newrecipeapp.fragments.PostFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        val accountFragment = AccountFragment()
        val postFragment = PostFragment()

        makeCurrentFragment(homeFragment)

        binding.bottomNavigation.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home -> makeCurrentFragment(homeFragment)
                R.id.post -> makeCurrentFragment(postFragment)
                R.id.Account -> makeCurrentFragment(accountFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }


}