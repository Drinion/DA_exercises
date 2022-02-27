#!/usr/bin/env python
# coding: utf-8

# In[1]:


import pandas as pd


# In[2]:


import matplotlib.pyplot as plt


# In[3]:


plt.rcParams["figure.autolayout"] = True


# In[4]:


df_insertion = pd.read_csv('/Users/dariomarti/Projects/DA_exercises/DA_ex01/time_insertion_sort.csv')


# In[5]:


df_insertion.set_index('#Elements').plot()


# In[6]:


df_merge = pd.read_csv('/Users/dariomarti/Projects/DA_exercises/DA_ex01/time_merge_sort.csv')


# In[7]:


df_merge.set_index('#Elements').plot()


# In[ ]:




