package lk.ac.kln.stu.ps2017050restapiassignment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import lk.ac.kln.stu.ps2017050restapi.model.Post
import lk.ac.kln.stu.ps2017050restapiassignment.adapter.PostAdapter
import lk.ac.kln.stu.ps2017050restapiassignment.api.PostApi
import lk.ac.kln.stu.ps2017050restapiassignment.databinding.FragmentPostsListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostsListFragment : Fragment() {

    companion object {
        private val TAG = PostsListFragment::class.java.simpleName
    }

    private var _binding: FragmentPostsListBinding? = null

    private val binding get() = _binding!!

    private var adapter: PostAdapter? = null
    private var posts: MutableList<Post> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerviewPost = binding.recyclerviewPosts

        adapter = PostAdapter(posts)
        recyclerviewPost.adapter = adapter

        fetchPosts()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun fetchPosts() {
        val call = PostApi.retrofitService.getPosts()
        call.enqueue(object: Callback<List<Post>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                Log.d(TAG, "Total posts: " + response.body()!!.size)

                val postsFromResponse = response.body()
                if(postsFromResponse != null) {
                    posts.addAll(postsFromResponse)
                    adapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e(TAG, "Got error : " + t.localizedMessage)
            }
        })

    }
}