package net.zeldadungeons.init.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySlingshotPellet extends Entity implements IProjectile {

	private Entity shootingEntity;
	private boolean inGround;
	private int ticksInGround;
	private int ticksInAir;

	public EntitySlingshotPellet(World worldIn) {
		super(worldIn);
		this.setSize(0.4F, 0.4F);

	}

	public EntitySlingshotPellet(World worldIn, double x, double y, double z) {
		this(worldIn);
		this.setPosition(x, y, z);

	}

	public EntitySlingshotPellet(World worldIn, EntityLivingBase shooter) {
		this(worldIn, shooter.posX, shooter.posY + shooter.getEyeHeight() - 0.15000000149011612D,
				shooter.posZ);
		this.shootingEntity = shooter;
		this.setAim(shooter, shooter.rotationPitch, shooter.rotationYaw, 1F, 1.5F, 0);
	}

	public void setAim(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
		float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		float f1 = -MathHelper.sin(pitch * 0.017453292F);
		float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		this.setThrowableHeading(f, f1, f2, velocity, inaccuracy);
		this.motionX += shooter.motionX;
		this.motionZ += shooter.motionZ;

		if (!shooter.onGround) {
			this.motionY += shooter.motionY;
		}
	}

	@Override
	public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) {
		float f = MathHelper.sqrt(x * x + y * y + z * z);
		x = x / f;
		y = y / f;
		z = z / f;
		this.motionX = x * velocity;
		this.motionY = y * velocity;
		this.motionZ = z * velocity;
	}

	@Override
	public void onUpdate() {

		super.onUpdate();
		BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
		IBlockState iblockstate = this.world.getBlockState(blockpos);
		Block block = iblockstate.getBlock();
		if (iblockstate.getMaterial() != Material.AIR) {
			AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(this.world, blockpos);
			if (axisalignedbb != Block.NULL_AABB
					&& axisalignedbb.offset(blockpos).contains(new Vec3d(this.posX, this.posY, this.posZ))) {
				this.inGround = true;
				motionX = 0;
				motionY = 0;
				motionZ = 0;
				int j = block.getMetaFromState(iblockstate);

				if (!this.world.collidesWithAnyBlock(this.getEntityBoundingBox().grow(0.05D))) {
					this.inGround = false;
					this.ticksInGround = 0;
					this.ticksInAir = 0;
				} else {
					++this.ticksInGround;
					this.doBlockCollisions();
				}
			}
		}
		double j = 0;

		if (!this.inGround) {
			ticksInAir++;
		}

		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;

		float f1 = 0.99F;
		this.motionX *= f1;
		this.motionY *= f1;
		this.motionZ *= f1;
		this.motionY -= 0.05000000074505806D;
		this.updatePos(posX, posY, posZ);
		this.removeEntity();
	}

	public void updatePos(double d1, double d2, double d3) {
		this.setPosition(d1, d2, d3);
	}

	public void removeEntity() {
		if (this.ticksInGround >= 10) {
			this.setDead();
			this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX, this.posY + 0.5D, this.posZ, 1.0D, 0.0D,
					1.0D, 10);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean isInRangeToRenderDist(double distance) {
		double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 10.0D;

		if (Double.isNaN(d0)) {
			d0 = 1.0D;
		}

		d0 = d0 * 64.0D * getRenderDistanceWeight();
		return distance < d0 * d0;
	}

	@Override
	protected void entityInit() {

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {

	}

}
