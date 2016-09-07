/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.architecture.blueprints.todoapp.data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.Nullable;

import com.example.android.architecture.blueprints.todoapp.BR;
import com.google.common.base.Objects;

import java.util.UUID;

/**
 * Immutable model class for a Task.
 * KEY 非静态的类，不能生成BR的属性。即便成员变量是静态的也不可以。
 * 之所以尝试失败，是因为 @Bindable 没有写在真正的Setter上。
 */
public class Task extends BaseObservable {


    private String id;

    @Nullable
    private String title;

    public void setTitle(@Nullable String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Nullable
    private String description;



    private boolean mCompleted;

    /**
     * Use this constructor to create a new active Task.
     *
     * @param title
     * @param description
     */
    public Task(@Nullable String title, @Nullable String description) {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        mCompleted = false;
    }

    /**
     * Use this constructor to create an active Task if the Task already has an id (copy of another
     * Task).
     *
     * @param title
     * @param description
     * @param id          of the class
     */
    public Task(@Nullable String title, @Nullable String description, String id) {
        this.id = id;
        this.title = title;
        this.description = description;
        mCompleted = false;
    }

    /**
     * Use this constructor to create a new completed Task.
     *
     * @param title
     * @param description
     * @param completed
     */
    public Task(@Nullable String title, @Nullable String description, boolean completed) {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        mCompleted = completed;
    }

    /**
     * Use this constructor to specify a completed Task if the Task already has an id (copy of
     * another Task).
     *
     * @param title
     * @param description
     * @param id
     * @param completed
     */
    public Task(@Nullable String title, @Nullable String description, String id, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        mCompleted = completed;
    }

    public String getId() {
        return id;
    }

    @Nullable
    @Bindable
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getTitleForList() {
        if (title != null && !title.equals("")) {
            return title;
        } else {
            return description;
        }
    }

    @Nullable
    @Bindable
    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    public boolean isActive() {
        return !mCompleted;
    }

    public boolean isEmpty() {
        return (title == null || "".equals(title)) &&
                (description == null || "".equals(description));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equal(id, task.id) &&
                Objects.equal(title, task.title) &&
                Objects.equal(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title, description);
    }

    @Override
    public String toString() {
        return "Task with title " + title;
    }

}
