package com.example.scanwellhealthproject.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scanwellhealthproject.DetailActivity
import com.example.scanwellhealthproject.R
import com.example.scanwellhealthproject.models.User
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Adapter class for displaying Users
 *
 * @property context Context
 * @property list ArrayList<User>
 * @constructor
 */
class UsersAdapter (private val context: Context, private val list: ArrayList<User>) :
RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    class UserViewHolder(private val context: Context, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

            fun bind(user: User){
                itemView.setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRAS_ACCOUNT_ID, user.accountId)
                    context.startActivity(intent)
                }
                Glide.with(context).load(user.profileImage).into(itemView.avatarImageView)
                itemView.nameTextView.text = user.displayName
                itemView.reputationTextView.text = user.reputation.toString()
                itemView.goldBadgeCountTextView.text = user.badgeCounts?.gold.toString()
                itemView.silverBadgeCountTextView.text = user.badgeCounts?.silver.toString()
                itemView.bronzeBadgeCountTextView.text = user.badgeCounts?.bronze.toString()
            }
    }

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return UserViewHolder(context, view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int = list.size

    fun updateUsers(userList: List<User>){
        list.clear()
        //val sortedList = userList.sortedBy { user -> user.reputation }
        list.addAll(userList)
        notifyDataSetChanged()
    }
}