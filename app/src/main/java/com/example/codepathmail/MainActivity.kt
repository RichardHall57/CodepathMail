package com.example.codepathmail

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var emails: MutableList<Email> // Use MutableList to modify the list
    lateinit var adapter: EmailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lookup the RecyclerView in activity layout
        val emailsRv = findViewById<RecyclerView>(R.id.emailsRv)
        // Fetch the list of emails (initial 5 emails)
        emails = EmailFetcher.getEmails().toMutableList() // Convert to MutableList for updates

        // Create adapter passing in the list of emails
        adapter = EmailAdapter(emails)
        // Attach the adapter to the RecyclerView to populate items
        emailsRv.adapter = adapter
        // Set layout manager to position the items
        emailsRv.layoutManager = LinearLayoutManager(this)

        // Setup Load More button click listener
        findViewById<Button>(R.id.loadMoreBtn).setOnClickListener {
            // Fetch next 5 emails
            val newEmails = EmailFetcher.getNext5Emails()

            // Add new emails to existing list of emails
            emails.addAll(newEmails)

            // Notify the adapter that there is a change in the data set
            adapter.notifyDataSetChanged()
        }
    }
}