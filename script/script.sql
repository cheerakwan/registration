CREATE TABLE [dbo].[USERS](
	[user_id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_name] [nvarchar](50) NOT NULL,
	[password] [nvarchar](100) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[mobile] [nvarchar](15) NOT NULL,
	[salary] [bigint] NOT NULL,
	[member_type] [nvarchar](10) NOT NULL,
	[create_by] [nvarchar](50) NULL,
	[create_time] [datetime] NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


