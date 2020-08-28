package com.example.restapi

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.restapi.model.pojo.Post
import com.example.restapi.model.remote.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.new_post_dialog.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    private var postsList =  ArrayList<Post>()
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewAdapter = PostAdapter(postsList)
        postsRecyclerView.adapter = viewAdapter

        loadApiData()
        //delete()

        addPost.setOnClickListener {
            showDialog()
        }

    }

    private fun loadApiData() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllPosts()

        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(
                call: Call<List<Post>>,
                response: Response<List<Post>>
            ) {
                response.body()?.map {
                    Log.d("MAIN", "${it.id} - ${it.title}")
                    postsList.add(it)
                }
                viewAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("MAIN", "Error: " + t)
                Toast.makeText(
                    applicationContext,
                    "Error: no pudimos recuperar los posts desde el api",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    private fun delete() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.deletePost(1)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d("DELETE", response.code().toString())
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("ERROR", t.message.toString())
            }

        })
    }

    private fun makePost(post: Post) {
        val service = RetrofitClient.retrofitInstance()
        val call = service.createNewPost(post)
        call.enqueue(object :  Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                when (response.code()){
                    in 200..299 -> {
                        Toast.makeText(this@MainActivity, "Post Realizado"
                            , Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(this@MainActivity, "Algo ocurrio, Post no creado"
                            , Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("MAIN", t.message.toString())
            }
        })
    }


    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.new_post_dialog)

        dialog.sendBtn.setOnClickListener {
            val title = dialog.titleEt.text.toString()
            val body  = dialog.bodyEt.text.toString()
            val userId = dialog.userIDET.text.toString().toLong()
            val post = Post(userId, 100, title, body)
            makePost(post)
            dialog.dismiss()
        }
        dialog.show()
    }
}